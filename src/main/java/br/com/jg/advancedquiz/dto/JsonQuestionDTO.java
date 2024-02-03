package br.com.jg.advancedquiz.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class JsonQuestionDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @JsonProperty("results")
    private List<JsonQuestionDTO> results;
    @JsonProperty("difficulty")
    private String difficulty;
    @JsonProperty("category")
    private String category;
    @JsonProperty("question")
    private String question;
    @JsonProperty("correct_answer")
    private String correct_answer;
    @JsonProperty("incorrect_answers")
    private String[] incorrect_answers;
}
