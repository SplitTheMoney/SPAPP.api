package ajm.spapp.api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name= "SharedFolder")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SharedFolder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Name", nullable = false, length = 150)
    private String name;

    @Column(name="Path", nullable = false, length= 150)
    private String path;

    @Column(name="Description", nullable = false, length=150)
    private String description;

}
