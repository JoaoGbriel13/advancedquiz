package br.com.jg.advancedquiz.dto;

import br.com.jg.advancedquiz.model.Alternative;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class QuestionDTO {
    private Long id;
    private String difficulty;
    private String category;
    private String question;
    private Set<Alternative> alternatives;
    private long correctQuestionAlternativeID;

}
