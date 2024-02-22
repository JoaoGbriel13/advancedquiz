package br.com.jg.advancedquiz.repository;

import br.com.jg.advancedquiz.model.GameplayModel;
import br.com.jg.advancedquiz.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GameplayModelRepository extends JpaRepository<GameplayModel, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM Gameplay WHERE user_finished = false AND player_id = :playerid  ")
    Optional<Boolean> findByFinished(@Param("playerid") long id);

    List<GameplayModel> findByPlayer(Player player);
    @Query(nativeQuery = true, value = "SELECT highest_score FROM GAMEPLAY where id = :gameplayId")
    Long findHighestScore(@Param("gameplayId") long gameplayId);

    List<GameplayModel> findByPlayerAndUserFinishedIsFalse(Player player);
}