package T360.Calenderservice.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "AgentSettings")
public class AgentSettings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "AgentID")
    private Long agentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AgentID", insertable = false, updatable = false)
    private Agent agent;

    @Column(name = "Type")
    private Integer typeCode;

    @Transient
    private RoleType type;

    public enum RoleType {
        SUPERVISEUR(1),
        ENQUETEUR(2);

        private final int code;

        RoleType(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public static RoleType fromCode(int code) {
            return switch (code) {
                case 1 -> SUPERVISEUR;
                case 2 -> ENQUETEUR;
                default -> throw new IllegalArgumentException("Unknown role code: " + code);
            };
        }
    }

    // Getters and setters...
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getAgentId() { return agentId; }
    public void setAgentId(Long agentId) { this.agentId = agentId; }
    public Agent getAgent() { return agent; }
    public void setAgent(Agent agent) { this.agent = agent; }
    public RoleType getType() {
        if (type == null && typeCode != null) {
            type = RoleType.fromCode(typeCode);
        }
        return type;
    }
    public void setType(RoleType type) {
        this.type = type;
        this.typeCode = (type != null) ? type.getCode() : null;
    }
    public Integer getTypeCode() { return typeCode; }
    public void setTypeCode(Integer typeCode) {
        this.typeCode = typeCode;
        this.type = (typeCode != null) ? RoleType.fromCode(typeCode) : null;
    }
}