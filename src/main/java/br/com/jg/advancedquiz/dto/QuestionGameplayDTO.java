package br.com.jg.advancedquiz.dto;

import br.com.jg.advancedquiz.model.GameplayModel;
import br.com.jg.advancedquiz.model.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QuestionGameplayDTO {
    private Long id;
    private GameplayModel gameplayModel;
    private Question question;
    private boolean active;
    private boolean playerWon;

    public QuestionGameplayDTO(Question question, boolean active) {
    }
}
