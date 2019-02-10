package pl.lukas121213.service;

import pl.lukas121213.exception.VotingException;
import pl.lukas121213.model.VotingInfo;
import pl.lukas121213.model.VotingType;

public interface VotingService {

    /**
     * Add vote for project.
     * If project is closed for voting, project or voter not exist method throw {@link VotingException}.
     *
     * @param votingInfo information about project, voter and {@link VotingType}
     */
    void voting(VotingInfo votingInfo);
}
