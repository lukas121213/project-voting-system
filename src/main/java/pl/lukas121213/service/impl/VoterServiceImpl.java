package pl.lukas121213.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lukas121213.model.Voter;
import pl.lukas121213.repository.VoterRepository;
import pl.lukas121213.service.VoterService;

import java.util.List;
import java.util.Optional;

@Service
public class VoterServiceImpl implements VoterService {

    private Logger logger = LoggerFactory.getLogger(VoterServiceImpl.class);

    @Autowired
    private VoterRepository voterRepository;

    @Override
    public List<Voter> getVoters() {
        return voterRepository.findAll();
    }

    @Override
    public long addVoter(Voter voter) {
        return voterRepository.save(voter).getId();
    }

    @Override
    public void deleteVoter(long voterId) {
        Optional<Voter> voter = voterRepository.findById(voterId);
        if (voter.isPresent())
            voterRepository.delete(voter.get());
        else
            logger.warn("Voter does not exist voterId=" + voterId);
    }
}
