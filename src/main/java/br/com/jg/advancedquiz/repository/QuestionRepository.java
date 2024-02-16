package br.com.jg.advancedquiz.repository;

import br.com.jg.advancedquiz.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    Optional<Question> findByQuestion(String question);
    @Query(nativeQuery = true, value = "SELECT * from Questions ORDER BY RANDOM() LIMIT :amount")
    List<Question> getQuestionsByAmount(@Param("amount") long amount);

    @Query(nativeQuery = true, value = "SELECT * FROM QUESTIONS WHERE difficulty = :difficulty ORDER BY RANDOM() LIMIT :amount")
    List<Question> getQuestionsByDifficulty(String difficulty, @Param("amount") long amount);
}
