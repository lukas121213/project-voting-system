package pl.lukas121213.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.lukas121213.model.ProjectVoter;

public interface VotingRepository extends JpaRepository<ProjectVoter, Long> {
}
