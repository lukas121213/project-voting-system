package pl.lukas121213.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.lukas121213.exception.VotingException;
import pl.lukas121213.model.*;
import pl.lukas121213.service.ProjectService;
import pl.lukas121213.service.VoterService;
import pl.lukas121213.service.VotingService;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class VotingServiceImplTest {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private VoterService voterService;

    @Autowired
    private VotingService votingService;

    @Test
    public void testVoting() {
        long projectId = projectService.addProject(TestUtils.prepareProject(1));
        long voterId = voterService.addVoter(TestUtils.prepareVoter(1));
        votingService.voting(prepareVotingInfo(projectId, voterId, VotingType.VOTING_FOR));
        // Is project voted for?
        checkProjectVotingStatus(projectId, 1 ,0);

        votingService.voting(prepareVotingInfo(projectId, voterId, VotingType.VOTING_FOR));
        votingService.voting(prepareVotingInfo(projectId, voterId, VotingType.VOTING_AGAINST));
        // If next voter voting for the same project not changing status?
        checkProjectVotingStatus(projectId, 1 , 0);

        long voterId2 = voterService.addVoter(TestUtils.prepareVoter(2));
        votingService.voting(prepareVotingInfo(projectId, voterId2, VotingType.VOTING_AGAINST));
        checkProjectVotingStatus(projectId, 1 , 1);

        long voterId3 = voterService.addVoter(TestUtils.prepareVoter(3));
        votingService.voting(prepareVotingInfo(projectId, voterId3, VotingType.VOTING_AGAINST));
        checkProjectVotingStatus(projectId, 1 , 2);
    }

    @Test(expected = VotingException.class)
    public void testClosedVoting() {
        long voterId = voterService.addVoter(TestUtils.prepareVoter(1));
        long projectId = projectService.addProject(TestUtils.prepareProject(1));
        projectService.closeVoting(projectId);
        votingService.voting(prepareVotingInfo(projectId, voterId, VotingType.VOTING_FOR));
    }

    @Test(expected = VotingException.class)
    public void testVotingByInvalidVoter() {
        long projectId = projectService.addProject(TestUtils.prepareProject(1));
        votingService.voting(prepareVotingInfo(projectId, 1, VotingType.VOTING_FOR));
    }

    @Test(expected = VotingException.class)
    public void testVotingForInvalidProject() {
        long voterId = voterService.addVoter(TestUtils.prepareVoter(1));
        votingService.voting(prepareVotingInfo(1, voterId, VotingType.VOTING_FOR));
    }

    private VotingInfo prepareVotingInfo(long projectId, long voterId, VotingType votingType) {
        VotingInfo votingInfo = new VotingInfo();
        votingInfo.setProjectId(projectId);
        votingInfo.setVoterId(voterId);
        votingInfo.setVotingType(votingType);
        return votingInfo;
    }

    private void checkProjectVotingStatus(long projectId, int expectedVotesFor, int expectedVotesAgainst) {
        ProjectInfo projectInfo = projectService.getProjectInfo(projectId);
        Assert.assertEquals(expectedVotesFor, projectInfo.getNumberVotesFor());
        Assert.assertEquals(expectedVotesAgainst, projectInfo.getNumberVotesAgainst());
    }
}
