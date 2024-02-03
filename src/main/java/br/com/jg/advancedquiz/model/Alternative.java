package br.com.jg.advancedquiz.model;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "Alternatives")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Alternative {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String description;
    private boolean isCorrect;

    public Alternative(String description, boolean isCorrect) {
        this.description = description;
        this.isCorrect = isCorrect;
    }
}
