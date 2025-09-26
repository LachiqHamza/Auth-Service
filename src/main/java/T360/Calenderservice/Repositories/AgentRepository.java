package T360.Calenderservice.Repositories;

import T360.Calenderservice.Entities.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AgentRepository extends JpaRepository<Agent, Long> {
    Optional<Agent> findByUsername(String username);
    @Query("SELECT DISTINCT a.username FROM Agent a WHERE a.username IS NOT NULL")
    List<String> findAllUsernames();
}
