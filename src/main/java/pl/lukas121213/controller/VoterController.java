package pl.lukas121213.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.lukas121213.model.Voter;
import pl.lukas121213.service.VoterService;

import java.util.List;

@Api(value="voterController", description="Operations associated with voter.")
@RestController
@RequestMapping("/voting/voter")
public class VoterController {

    @Autowired
    private VoterService voterService;

    @ApiOperation(value = "Get list all voters.", nickname = "getVoters")
    @GetMapping(value = "/list")
    public List<Voter> getVoters() {
        return voterService.getVoters();
    }

    @ApiOperation(value = "Add voter.", nickname = "addVoter")
    @PostMapping(value ="/add", headers = {"Accept=application/json"})
    public long addVoter(@RequestBody Voter voter) {
        return voterService.addVoter(voter);
    }

    @ApiOperation(value = "Delete voter.", nickname = "deleteVoter")
    @DeleteMapping(value ="/delete/{voterId}")
    public void deleteVoter(@PathVariable long voterId) {
        voterService.deleteVoter(voterId);
    }
}
