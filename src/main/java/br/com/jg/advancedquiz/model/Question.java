package br.com.jg.advancedquiz.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;


import java.util.List;
import java.util.Set;
@Entity
@Table(name = "Questions")
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String difficulty;
    private String category;
    private String question;
    @OneToMany(targetEntity = Alternative.class)
    @ToString.Exclude
    private Set<Alternative> alternatives;
    private long correctQuestionAlternativeID;

}
