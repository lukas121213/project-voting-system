package pl.lukas121213.service.impl;

import pl.lukas121213.model.Project;
import pl.lukas121213.model.Voter;

class TestUtils {

    static Project prepareProject(int idx) {
        Project project = new Project();
        project.setName("project" + idx);
        project.setDescription("This is the project" + idx);
        project.setVotingOpen(true);
        return project;
    }

    static Voter prepareVoter(int idx) {
        Voter voter = new Voter();
        voter.setFirstName("firstname" + idx);
        voter.setSurname("surname" + idx);
        return voter;
    }
}
