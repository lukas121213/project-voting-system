package pl.lukas121213.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lukas121213.exception.ExceptionMessage;
import pl.lukas121213.exception.VotingException;
import pl.lukas121213.model.Project;
import pl.lukas121213.model.ProjectVoter;
import pl.lukas121213.model.Voter;
import pl.lukas121213.model.VotingInfo;
import pl.lukas121213.repository.ProjectRepository;
import pl.lukas121213.repository.VotingRepository;
import pl.lukas121213.repository.VoterRepository;
import pl.lukas121213.service.VotingService;

import java.util.Collections;

@Service
public class VotingServiceImpl implements VotingService {

    private Logger logger = LoggerFactory.getLogger(VotingServiceImpl.class);

    @Autowired
    private VoterRepository voterRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private VotingRepository votingRepository;

    @Override
    public void voting(VotingInfo votingInfo) {
        Voter voter = getVoterByIdOrThrow(votingInfo.getVoterId());
        Project project = getProjectByIdOrThrow(votingInfo.getProjectId());

        boolean isNotVoted = checkIfNotVoted(voter, votingInfo.getProjectId());
        if (isNotVoted) {
            if (!project.isVotingOpen())
               throw new VotingException(ExceptionMessage.PROJECT_VOTING_CLOSED);

            ProjectVoter projectVoter = new ProjectVoter.Builder()
                .setVoter(voter)
                .setProject(project)
                .setVotingType(votingInfo.getVotingType())
                .build();
            votingRepository.save(projectVoter);
        } else {
            logger.warn("Project=" + project + " already voted by voter=" + voter);
        }
    }

    private Voter getVoterByIdOrThrow(long voterId) {
        return voterRepository
            .findById(voterId)
            .orElseThrow(() -> new VotingException(ExceptionMessage.VOTER_NOT_FOUND));
    }

    private Project getProjectByIdOrThrow(long projectId) {
        return projectRepository
            .findById(projectId)
            .orElseThrow(() -> new VotingException(ExceptionMessage.PROJECT_NOT_FOUND));
    }

    private boolean checkIfNotVoted(Voter voter, long projectId) {
        return voter.getProjects()
            .orElse(Collections.emptySet())
            .stream().noneMatch(projectVoter -> projectVoter.getProject().getId().equals(projectId));
    }
}