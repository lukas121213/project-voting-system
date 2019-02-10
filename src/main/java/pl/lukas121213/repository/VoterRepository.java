package pl.lukas121213.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.lukas121213.model.Voter;

public interface VoterRepository extends JpaRepository<Voter, Long> {
}
