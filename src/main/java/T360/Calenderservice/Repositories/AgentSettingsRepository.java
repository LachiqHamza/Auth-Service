package T360.Calenderservice.Repositories;

import T360.Calenderservice.Entities.AgentSettings;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgentSettingsRepository extends JpaRepository<AgentSettings, Long> {
    Optional<AgentSettings> findFirstByAgentId(Long agentId);
}