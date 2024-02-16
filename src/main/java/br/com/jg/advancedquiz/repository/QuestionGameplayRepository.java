package br.com.jg.advancedquiz.repository;

import br.com.jg.advancedquiz.model.QuestionGameplay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface QuestionGameplayRepository extends JpaRepository<QuestionGameplay, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM question_gameplay WHERE active = true and gameplay_model_id = :gameplayId")
    QuestionGameplay getActiveQuestion(@Param("gameplayId") long gameplayId);
    @Query(nativeQuery = true, value = "SELECT * FROM question_gameplay WHERE id = :questionId and gameplay_model_id = :gameplayId")
    Optional<QuestionGameplay> nextQuestion(@Param("questionId") long questionId, long gameplayId);
}