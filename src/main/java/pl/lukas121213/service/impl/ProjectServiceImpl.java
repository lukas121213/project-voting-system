package pl.lukas121213.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.lukas121213.exception.ExceptionMessage;
import pl.lukas121213.exception.VotingException;
import pl.lukas121213.model.Project;
import pl.lukas121213.model.ProjectInfo;
import pl.lukas121213.model.VotingType;
import pl.lukas121213.repository.ProjectRepository;
import pl.lukas121213.service.ProjectService;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    private Logger logger = LoggerFactory.getLogger(ProjectServiceImpl.class);

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public List<Project> getProjects() {
        return projectRepository.findAll(new Sort(Sort.Direction.ASC, Project.NAME_COLUMN));
    }

    @Override
    public long addProject(Project project) {
        Project existProject = projectRepository.findOneByName(project.getName());
        if (existProject != null)
            return existProject.getId();
        return projectRepository.save(project).getId();
    }

    @Override
    public void deleteProject(long projectId) {
        Optional<Project> project = projectRepository.findById(projectId);
        if (project.isPresent())
            projectRepository.delete(project.get());
        else
            logger.warn("Project does not exist projectId=" + projectId);
    }

    @Override
    public ProjectInfo getProjectInfo(long projectId) {
        Project project = getProjectByIdOrThrow(projectId);
        return new ProjectInfo.Builder(project)
            .setNumberVotesFor(getVotersCountByVotingType(project, VotingType.VOTING_FOR))
            .setNumberVotesAgainst(getVotersCountByVotingType(project, VotingType.VOTING_AGAINST))
            .build();
    }

    @Override
    public void closeVoting(long projectId) {
        Project project = getProjectByIdOrThrow(projectId);
        if (project.isVotingOpen()) {
            project.setVotingOpen(false);
            projectRepository.save(project);
        }
    }

    private Project getProjectByIdOrThrow(long projectId) {
        return projectRepository.findById(projectId).orElseThrow(() -> new VotingException(ExceptionMessage.PROJECT_NOT_FOUND));
    }

    private long getVotersCountByVotingType(Project project, VotingType votingType) {
        return project.getVoters()
            .stream()
            .filter(projectVoter -> projectVoter.getVotingType().equals(votingType)).count();
    }
}
