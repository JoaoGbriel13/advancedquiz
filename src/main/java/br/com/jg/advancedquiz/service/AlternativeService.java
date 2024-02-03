package br.com.jg.advancedquiz.service;

import br.com.jg.advancedquiz.dto.JsonQuestionDTO;
import br.com.jg.advancedquiz.model.Alternative;
import br.com.jg.advancedquiz.model.Question;
import br.com.jg.advancedquiz.repository.AlternativeRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AlternativeService {
    private final AlternativeRepository alternativeRepository;

    public AlternativeService(AlternativeRepository alternativeRepository) {
        this.alternativeRepository = alternativeRepository;
    }

    public Set<Alternative> alternativeSet(JsonQuestionDTO question){
        Set<Alternative> myAlternatives = new HashSet<>();
        Alternative correctAlt = new Alternative(question.getCorrect_answer(),true);
        myAlternatives.add(correctAlt);
        alternativeRepository.save(correctAlt);
        for (String answer : question.getIncorrect_answers()){
            Alternative alt = new Alternative(answer,false);
            myAlternatives.add(alt);
            alternativeRepository.save(alt);
        }
        return myAlternatives;
    }
}
