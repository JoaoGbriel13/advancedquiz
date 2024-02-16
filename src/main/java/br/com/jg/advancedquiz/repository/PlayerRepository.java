package br.com.jg.advancedquiz.repository;

import br.com.jg.advancedquiz.model.Player;
import br.com.jg.advancedquiz.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    Optional<Player> findByNicknameAndPassword(String nickname, String password);
}