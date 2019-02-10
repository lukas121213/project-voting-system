package pl.lukas121213.service;

import pl.lukas121213.model.Voter;

import java.util.List;

public interface VoterService {

    /**
     * Return list all {@link Voter} stored in database.
     *
     * @return list {@link Voter}.
     */
    List<Voter> getVoters();

    /**
     * Save {@link Voter} in database.
     *
     * @param voter voter to save
     * @return id saved voter.
     */
    long addVoter(Voter voter);

    /**
     * Delete {@link Voter} from database.
     *
     * @param voterId voter id
     */
    void deleteVoter(long voterId);
}
