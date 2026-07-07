package ajm.spapp.api.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table (name = "Users")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(name = "Name", nullable = false, length = 100)
    private String name;

    @Column(name = "email", nullable = false, unique = true, length = 150)
    private String email;

    @Column(name = "PasswordHash", length = 100)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    private Role role;

    public Long getId() {
        return id;
    }

    public void setStatus(RequestStatus requestStatus) {
    }

    public void setManager(User manager) {
    }

    public void setDecisionDate(LocalDateTime now) {
    }
}
