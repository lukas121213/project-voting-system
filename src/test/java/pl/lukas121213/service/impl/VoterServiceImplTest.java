package pl.lukas121213.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.lukas121213.service.VoterService;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class VoterServiceImplTest {

    @Autowired
    private VoterService voterService;

    @Test
    public void testRemoveVoter() {
        voterService.addVoter(TestUtils.prepareVoter(1));
        long voterId = voterService.addVoter(TestUtils.prepareVoter(2));
        Assert.assertEquals(2, voterService.getVoters().size());

        voterService.deleteVoter(voterId);
        Assert.assertEquals(1, voterService.getVoters().size());
    }
}
