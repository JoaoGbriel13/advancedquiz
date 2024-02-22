package br.com.jg.advancedquiz.service;

import br.com.jg.advancedquiz.dto.JsonQuestionDTO;
import br.com.jg.advancedquiz.mapper.QuestionMapper;
import br.com.jg.advancedquiz.model.Alternative;
import br.com.jg.advancedquiz.model.Question;
import br.com.jg.advancedquiz.repository.AlternativeRepository;
import br.com.jg.advancedquiz.repository.QuestionRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class QuestionService {


    private final QuestionRepository questionRepository;
    private final QuestionJsonService questionJsonService;
    private final QuestionMapper questionMapper;
    private final AlternativeRepository alternativeRepository;
    private final AlternativeService alternativeService;


    public QuestionService(QuestionRepository questionRepository, QuestionJsonService questionJsonService, QuestionMapper questionMapper, AlternativeRepository alternativeRepository, AlternativeService alternativeService) {
        this.questionRepository = questionRepository;
        this.questionJsonService = questionJsonService;
        this.questionMapper = questionMapper;
        this.alternativeRepository = alternativeRepository;
        this.alternativeService = alternativeService;
    }

    public ResponseEntity getAllQuestions(){
        List<Question> question = questionRepository.findAll();
        if (question.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Nenhuma questão na lista");
        }else {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(questionMapper.questionDTOS(question));
        }
    }
    public ResponseEntity getOneQuestion(long id){
        Optional<Question> question = questionRepository.findById(id);
        if (question.isPresent()){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(questionMapper.questionDTO(question.get()));
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhuma questão encontrada com esse Id");
        }
    }


    public ResponseEntity saveQuestions() throws JsonProcessingException {
        List<JsonQuestionDTO> questions = questionJsonService.getFromApi();
        for (JsonQuestionDTO q : questions){
            Set<Alternative> alternativesSet = alternativeService.alternativeSet(q);
            Question questionToAdd = new Question();
            questionToAdd.setQuestion(StringEscapeUtils.escapeHtml4(q.getQuestion()));
            questionToAdd.setAlternatives(alternativesSet);
            questionToAdd.setCorrectQuestionAlternativeID(alternativeRepository.findByDescription(q.getCorrect_answer()).getId());
            questionToAdd.setDifficulty(q.getDifficulty());
            questionToAdd.setCategory(q.getCategory());
            if (questionRepository.findByQuestion(questionToAdd.getQuestion()).isEmpty()){
                questionRepository.save(questionToAdd);
            }else {
                questions.remove(q);
            }
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Saved");
    }
}
