package br.com.jg.advancedquiz.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Gameplay")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GameplayModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private QuestionGameplay questions;
    @ManyToOne
    private Player player;
    private long highestScore;
    private boolean userFinished;
    @Column(columnDefinition = "bigint default 0")
    private long questionsCorrectCount;
}
