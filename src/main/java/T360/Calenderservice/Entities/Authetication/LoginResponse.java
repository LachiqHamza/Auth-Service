package T360.Calenderservice.Entities.Authetication;

public class LoginResponse {
    private String token;
    private String role;

    public LoginResponse(String token, String role) {
        this.token = token;
        this.role = role;
    }

    // getters
    public String getToken() { return token; }
    public String getRole() { return role; }
}
