package pl.lukas121213.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.lukas121213.model.VotingInfo;
import pl.lukas121213.service.VotingService;

@Api(value="votingController", description="Operations associated with voting.")
@RestController
@RequestMapping("/voting")
public class VotingController {

    @Autowired
    private VotingService votingService;

    @ApiOperation(value = "Add vote if project is not closed for voting.", nickname = "voting")
    @PostMapping(value ="/add", headers = {"Accept=application/json"})
    public void voting(@RequestBody VotingInfo votingInfo) {
        votingService.voting(votingInfo);
    }
}
