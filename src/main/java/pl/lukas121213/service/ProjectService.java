package pl.lukas121213.service;

import pl.lukas121213.model.Project;
import pl.lukas121213.model.ProjectInfo;

import java.util.List;

public interface ProjectService {

    /**
     * Return list all {@link Project} stored in database.
     *
     * @return list {@link Project}.
     */
    List<Project> getProjects();

    /**
     * Save {@link Project} if not exist in database.
     *
     * @param project project to save
     * @return id saved project or id existed project.
     */
    long addProject(Project project);

    /**
     * Delete {@link Project} from database.
     *
     * @param projectId project id
     */
    void deleteProject(long projectId);

    /**
     * Return information for given {@link Project}.
     *
     * @param projectId project id
     * @return information about {@link Project}.
     */
    ProjectInfo getProjectInfo(long projectId);

    /**
     * Close voting for given {@link Project} if open.
     *
     * @param projectId project id
     */
    void closeVoting(long projectId);
}
