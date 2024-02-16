package br.com.jg.advancedquiz.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QuestionGameplay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private GameplayModel gameplayModel;
    @ManyToOne
    private Question question;
    private boolean active;
    private boolean playerWon;

    public QuestionGameplay(Question question, boolean active) {
    }
}
