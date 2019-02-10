package pl.lukas121213.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.lukas121213.model.Project;
import pl.lukas121213.service.ProjectService;

import java.util.List;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ProjectServiceImplTest {

    @Autowired
    private ProjectService projectService;

    @Test
    public void testSortProjects() {
        Project project1 = TestUtils.prepareProject(3);
        projectService.addProject(project1);

        Project project2 = TestUtils.prepareProject(2);
        projectService.addProject(project2);

        Project project3 = TestUtils.prepareProject(1);
        projectService.addProject(project3);
        // Is project sorted by name ?
        List<Project> projects = projectService.getProjects();

        Assert.assertEquals(project3.getName(), projects.get(0).getName());
        Assert.assertEquals(project2.getName(), projects.get(1).getName());
        Assert.assertEquals(project1.getName(), projects.get(2).getName());
    }

    @Test
    public void testDoubleAddingProjectWithTheSameName() {
        long projectId1 = projectService.addProject(TestUtils.prepareProject(1));
        long projectId2 = projectService.addProject(TestUtils.prepareProject(1));
        Assert.assertEquals(projectId1, projectId2);
    }

    @Test
    public void testRemoveProject() {
        projectService.addProject(TestUtils.prepareProject(1));
        long projectId = projectService.addProject(TestUtils.prepareProject(2));
        Assert.assertEquals(2, projectService.getProjects().size());

        projectService.deleteProject(projectId);
        Assert.assertEquals(1, projectService.getProjects().size());
    }
}
