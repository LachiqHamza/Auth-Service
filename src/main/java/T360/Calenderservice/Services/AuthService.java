package T360.Calenderservice.Services;

import T360.Calenderservice.Dto.LoginRequest;
import T360.Calenderservice.Entities.Authetication.LoginResponse;
import T360.Calenderservice.Entities.Agent;
import T360.Calenderservice.Entities.AgentSettings;
import T360.Calenderservice.Repositories.AgentRepository;
import T360.Calenderservice.Repositories.AgentSettingsRepository;
import T360.Calenderservice.Util.JwtUtil;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AuthService {

    private final AgentRepository agentRepository;
    private final AgentSettingsRepository settingsRepository;
    private final JwtUtil jwtUtil;

    // In-memory blacklist of invalidated tokens
    private final Set<String> blacklistedTokens = new HashSet<>();

    public AuthService(AgentRepository agentRepository,
                       AgentSettingsRepository settingsRepository,
                       JwtUtil jwtUtil) {
        this.agentRepository = agentRepository;
        this.settingsRepository = settingsRepository;
        this.jwtUtil = jwtUtil;
    }

    public LoginResponse login(LoginRequest request) {
        Agent agent = agentRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!agent.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        AgentSettings setting = settingsRepository.findFirstByAgentId(agent.getId())
                .orElse(null);

        String role = setting != null ? setting.getType().name() : "UNKNOWN";

        String token = jwtUtil.generateToken(agent.getUsername(), role);

        return new LoginResponse(token, role);
    }

    // Use the custom query method instead of findAll()
    public List<String> getAllUsernames() {
        try {
            return agentRepository.findAllUsernames();
        } catch (Exception e) {
            // Log the error and return empty list
            System.err.println("Error fetching usernames: " + e.getMessage());
            return List.of();
        }
    }

    public boolean logout(String token) {
        return blacklistedTokens.add(token);
    }

    public boolean isTokenBlacklisted(String token) {
        return blacklistedTokens.contains(token);
    }
}