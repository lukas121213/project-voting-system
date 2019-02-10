package pl.lukas121213.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.lukas121213.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    Project findOneByName(String name);
}
