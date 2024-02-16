package br.com.jg.advancedquiz.dto;

import br.com.jg.advancedquiz.model.Player;
import br.com.jg.advancedquiz.model.QuestionGameplay;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GameplayModelDTO {
    private Long id;
    @JsonIgnore
    private QuestionGameplay questions;
    private Player player;
    private long highestScore;
    private boolean userFinished;
    private long questionsCorrectCount;
}
