package br.com.jg.advancedquiz.controller;

import br.com.jg.advancedquiz.service.QuestionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/questions")
public class QuestionControler {
    private final QuestionService questionService;

    public QuestionControler(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/all")
    public ResponseEntity getAllQuestions() throws IOException {
        return questionService.getAllQuestions();
    }
    @GetMapping("/{id}")
    public ResponseEntity getOneQuestion(@PathVariable long id){
        return questionService.getOneQuestion(id);
    }
    @PostMapping()
    public ResponseEntity saveQuestions() throws JsonProcessingException {
        return questionService.saveQuestions();
    }

}
