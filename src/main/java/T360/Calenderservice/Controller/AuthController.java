package T360.Calenderservice.Controller;

import T360.Calenderservice.Dto.LoginRequest;
import T360.Calenderservice.Entities.Authetication.LoginResponse;
import T360.Calenderservice.Services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse response = authService.login(request);
        if (response.getToken() == null) {
            return ResponseEntity.status(401).body(response); // invalid login
        }
        return ResponseEntity.ok(response);
    }

    // Updated endpoint to return all non-null usernames
    @GetMapping("/usernames")
    public ResponseEntity<List<String>> getAllUsernames() {
        List<String> usernames = authService.getAllUsernames();
        return ResponseEntity.ok(usernames);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        boolean success = authService.logout(token);

        if (success) {
            return ResponseEntity.ok("Successfully logged out");
        } else {
            return ResponseEntity.badRequest().body("Token already invalidated");
        }
    }
}
