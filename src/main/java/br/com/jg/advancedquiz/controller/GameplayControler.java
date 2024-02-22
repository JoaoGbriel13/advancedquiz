package br.com.jg.advancedquiz.controller;

import br.com.jg.advancedquiz.dto.CheckAnswerDTO;
import br.com.jg.advancedquiz.dto.StartGameplayDTO;
import br.com.jg.advancedquiz.service.GameplayService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gameplay")
public class GameplayControler {
    private final GameplayService gameplayService;

    public GameplayControler(GameplayService gameplayService) {
        this.gameplayService = gameplayService;
    }

    @GetMapping("/get-gameplay/{id}")
    public ResponseEntity getGameplay(@PathVariable long id){
        return gameplayService.getGameplay(id);
    }
    @GetMapping("/activequestion/{gameplayId}")
    public ResponseEntity getActiveQuestion(@PathVariable long gameplayId){
        return gameplayService.getActiveQuestion(gameplayId);
    }
    @GetMapping("/score/{gameplayId}")
    public ResponseEntity getScore(@PathVariable long gameplayId){
        return gameplayService.getScore(gameplayId);
    }
    @PostMapping("/start")
    public ResponseEntity startGameplay(@RequestBody StartGameplayDTO startGameplayDTO){
        return gameplayService.startGameplay(startGameplayDTO);
    }
    @PostMapping("/check")
    public ResponseEntity checkCorrect(@RequestBody CheckAnswerDTO checkAnswerDTO){
        return gameplayService.checkAnswerCorrect(checkAnswerDTO);
    }

}
