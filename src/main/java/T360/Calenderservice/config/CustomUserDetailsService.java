package T360.Calenderservice.config;

import T360.Calenderservice.Entities.Agent;
import T360.Calenderservice.Repositories.AgentRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AgentRepository agentRepository;

    public CustomUserDetailsService(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Agent agent = agentRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Get role from single setting
        String role = agent.getSetting() != null ? agent.getSetting().getType().name() : "ENQUETEUR";

        return User.builder()
                .username(agent.getUsername())
                .password("{noop}" + agent.getPassword()) // {noop} disables encoding
                .roles(role)
                .build();
    }
}
