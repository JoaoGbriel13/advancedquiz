package br.com.jg.advancedquiz.service;


import br.com.jg.advancedquiz.dto.StartGameplayDTO;
import br.com.jg.advancedquiz.mapper.GameplayMapper;
import br.com.jg.advancedquiz.mapper.QuestionGameplayMapper;
import br.com.jg.advancedquiz.model.*;
import br.com.jg.advancedquiz.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class GameplayService {
    private final PlayerRepository playerRepository;
    private final QuestionRepository questionRepository;
    private final GameplayModelRepository gameplayModelRepository;
    private final QuestionGameplayRepository questionGameplayRepository;
    private final QuestionGameplayMapper questionGameplayMapper;
    private final GameplayMapper gameplayMapper;

    public GameplayService(PlayerRepository playerRepository, QuestionRepository questionRepository,
                           GameplayModelRepository gameplayModelRepository,
                           QuestionGameplayRepository questionGameplayRepository, QuestionGameplayMapper questionGameplayMapper, GameplayMapper gameplayMapper) {
        this.playerRepository = playerRepository;
        this.questionRepository = questionRepository;
        this.gameplayModelRepository = gameplayModelRepository;
        this.questionGameplayRepository = questionGameplayRepository;
        this.questionGameplayMapper = questionGameplayMapper;
        this.gameplayMapper = gameplayMapper;
    }

    public ResponseEntity startGameplay(StartGameplayDTO startGameplayDTO) {
        Player player = playerRepository.findById(startGameplayDTO.getUserId()).orElseThrow();
        if (!gameplayModelRepository.findByFinished(player.getId()).orElse(true)){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("O usuário tem um jogo ativo, finalize-o!");
        }
        GameplayModel gameplayModel = new GameplayModel();
        gameplayModel.setPlayer(player);
        List<QuestionGameplay> questionGameplays = assignQuestions(gameplayModel);
        for (QuestionGameplay q : questionGameplays){
            questionGameplayRepository.save(q);
            gameplayModel.setQuestions(q);
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(gameplayMapper.toDTO(gameplayModelRepository.save(gameplayModel)));
    }
    private List<QuestionGameplay> assignQuestions(GameplayModel gameplayModel){
        List<Question> questionsToPlay = questionRepository.getQuestionsByDifficulty("easy",6);
        questionsToPlay.addAll(questionRepository.getQuestionsByDifficulty("medium",5));
        questionsToPlay.addAll(questionRepository.getQuestionsByDifficulty("hard",5));
        List<QuestionGameplay> questionGameplays = new ArrayList<>();
        for (Question q : questionsToPlay){
            QuestionGameplay question = new QuestionGameplay();
            question.setQuestion(q);
            question.setGameplayModel(gameplayModel);
            question.setActive(false);
            questionGameplays.add(question);
        }
        questionGameplays.get(0).setActive(true);
        return questionGameplays;
    }

    public ResponseEntity getGameplay(long id){
        if (gameplayModelRepository.findById(id).isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhuma gameplay encontrada");
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(gameplayMapper.toDTO(gameplayModelRepository.findById(id).get()));
    }


    public ResponseEntity checkAnswerCorrect(long gameplayId, long selectedAlternativeId){
        Optional<GameplayModel> gameplayModel = gameplayModelRepository.findById(gameplayId);
        if (gameplayModel.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontrado");
        }
        if (!checkActiveQuestions(gameplayId) || gameplayModel.get().isUserFinished()){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Não há mais questões ativas para a sessão atual");
        }
        QuestionGameplay questionGameplay = questionGameplayRepository.getActiveQuestion(gameplayModel.get().getId());
        long actualScore = gameplayMapper.toDTO(gameplayModel.get()).getHighestScore();

        if (questionGameplay.getQuestion().getCorrectQuestionAlternativeID() == selectedAlternativeId){
            return handleCorrectAnswer(questionGameplay, gameplayModel.get());
        }else{
            gameplayModel.get().setUserFinished(true);
            gameplayModelRepository.save(gameplayModel.get());
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("ERRRRROU!\n Seu resultado é: " + actualScore + false );
        }
    }
    public ResponseEntity getActiveQuestion(long gameplayId){
        GameplayModel gameplayModel = gameplayModelRepository.findById(gameplayId).get();
        if (gameplayModel.isUserFinished()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhuma gameplay ativa encontrada");
        }
        QuestionGameplay questionGameplay = questionGameplayRepository.getActiveQuestion(gameplayModel.getId());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("A questão é:"+
                questionGameplayMapper.toDTO(questionGameplay)
                .getQuestion()
                .getQuestion() +
                "As alternativas são:" +
                questionGameplayMapper.toDTO(questionGameplay)
                        .getQuestion()
                        .getAlternatives()
                        .stream()
                        .map(a -> a.getId() + "- " + a.getDescription())
                        .toList()
        );
    }
    private ResponseEntity handleCorrectAnswer(QuestionGameplay questionGameplay, GameplayModel gameplayModel){
        questionGameplay.setActive(false);
        questionGameplay.setPlayerWon(true);
        gameplayModel.setQuestionsCorrectCount(gameplayModel.getQuestionsCorrectCount() + 1);
        long actualScore = handleScore(gameplayModel,questionGameplay);
        return checkWinner(actualScore, gameplayModel, questionGameplay);
    }

    private long questionScore(String difficulty){
        if (Objects.equals(difficulty, "easy")){
            return 1000;
        } else if (Objects.equals(difficulty, "medium")) {
            return 10000;
        }else {
            return 100000;
        }
    }

    private long handleScore(GameplayModel gameplayModel, QuestionGameplay questionGameplay) {
        long questionScore = questionScore(questionGameplay.getQuestion().getDifficulty());

        if (gameplayModel.getHighestScore() == 5000 || gameplayModel.getHighestScore() == 50000 || gameplayModel.getHighestScore() == 500000) {
            gameplayModel.setHighestScore(gameplayModel.getHighestScore() * 2);
        } else {
            gameplayModel.setHighestScore(gameplayModel.getHighestScore() + questionScore);
        }
        return gameplayModel.getHighestScore();
    }


    private boolean checkActiveQuestions(long gameplayId){
        return questionGameplayRepository.getActiveQuestion(gameplayId) != null;
    }

    private ResponseEntity checkWinner(long actualScore, GameplayModel gameplayModel, QuestionGameplay questionGameplay){
        if (actualScore == 1000000){
            gameplayModel.setUserFinished(true);
            gameplayModel.getPlayer().setWins(gameplayModel.getPlayer().getWins() + 1);
            gameplayModelRepository.save(gameplayModel);
            questionGameplayRepository.save(questionGameplay);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Parabens, você venceu o jogo!");
        }else {
            QuestionGameplay nextQuestion = questionGameplayRepository.nextQuestion(
                    questionGameplay.getId() +1,
                    gameplayModel.getId()
            ).get();
            nextQuestion.setActive(true);
            gameplayModelRepository.save(gameplayModel);
            questionGameplayRepository.save(questionGameplay);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Você acertou a questão! " + gameplayMapper.toDTO(gameplayModel)
                    .getHighestScore());
        }
    }


}
