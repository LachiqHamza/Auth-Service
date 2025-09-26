package T360.Calenderservice.Entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Agents")
public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AgentID")
    private Long id;

    @Column(name = "UserName", unique = true)
    private String username;

    @Column(name = "Password")
    private String password;

    @Column(name = "EncryptedPassword")
    private String encryptedPassword;

    @Column(name = "Email")
    private String email;

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "Name")
    private String lastName;

    @Column(name = "CreationTime")
    private LocalDateTime creationTime;

    @Column(name = "LastModificationTime")
    private LocalDateTime lastModificationTime;

    @PrePersist
    protected void onCreate() {
        creationTime = LocalDateTime.now();
        lastModificationTime = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        lastModificationTime = LocalDateTime.now();
    }

    // Fixed the relationship mapping
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AgentID", referencedColumnName = "AgentID")
    private AgentSettings setting;

    // Getters and setters...
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getEncryptedPassword() { return encryptedPassword; }
    public void setEncryptedPassword(String encryptedPassword) { this.encryptedPassword = encryptedPassword; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public LocalDateTime getCreationTime() { return creationTime; }
    public void setCreationTime(LocalDateTime creationTime) { this.creationTime = creationTime; }
    public LocalDateTime getLastModificationTime() { return lastModificationTime; }
    public void setLastModificationTime(LocalDateTime lastModificationTime) { this.lastModificationTime = lastModificationTime; }
    public AgentSettings getSetting() { return setting; }
    public void setSetting(AgentSettings setting) { this.setting = setting; }
}