package pl.lukas121213.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.lukas121213.model.Project;
import pl.lukas121213.model.ProjectInfo;
import pl.lukas121213.service.ProjectService;

import java.util.List;

@Api(value="projectController", description="Operations associated with project.")
@RestController
@RequestMapping("/voting/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @ApiOperation(value = "Get list all projects.", nickname = "getProjects")
    @GetMapping(value = "/list")
    public List<Project> getProjects() {
        return projectService.getProjects();
    }

    @ApiOperation(value = "Get information about project.", nickname = "getProjectInfo")
    @GetMapping(value = "/info/{projectId}")
    public ProjectInfo getProjectInfo(@PathVariable long projectId) {
        return projectService.getProjectInfo(projectId);
    }

    @ApiOperation(value = "Add project.", nickname = "addProject")
    @PostMapping(value ="/add", headers = {"Accept=application/json"})
    public long addProject(@RequestBody Project project) {
        return projectService.addProject(project);
    }

    @ApiOperation(value = "Delete project.", nickname = "deleteProject")
    @DeleteMapping(value ="/delete/{projectId}")
    public void deleteProject(@PathVariable long projectId) {
        projectService.deleteProject(projectId);
    }

    @ApiOperation(value = "Close project for voting.", nickname = "closeVoting")
    @GetMapping(value = "/close/{projectId}")
    public void closeVoting(@PathVariable long projectId) {
        projectService.closeVoting(projectId);
    }
}
