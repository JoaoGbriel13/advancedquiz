package br.com.jg.advancedquiz.service;

import br.com.jg.advancedquiz.dto.LoginDto;
import br.com.jg.advancedquiz.dto.RegisterDTO;
import br.com.jg.advancedquiz.mapper.PlayerMapper;
import br.com.jg.advancedquiz.model.Player;
import br.com.jg.advancedquiz.repository.PlayerRepository;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;
    private final Base64 base64;

    public PlayerService(PlayerRepository playerRepository, PlayerMapper playerMapper, Base64 base64) {
        this.playerRepository = playerRepository;
        this.playerMapper = playerMapper;
        this.base64 = base64;
    }
    public ResponseEntity getPlayer(long playerId){
        if (playerRepository.findById(playerId).isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum jogador encontrado com esse ID");
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(playerMapper.toDTO(playerRepository.findById(playerId).get()));
    }
    public ResponseEntity getAllplayers(){
        if(playerRepository.findAll().isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum jogador encontrado");
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(playerMapper.toListDTO(playerRepository.findAll()));
    }
    public ResponseEntity savePlayer(RegisterDTO registerDTO) throws EncoderException {
        Player player = new Player();
        player.setPassword(base64.encodeAsString(registerDTO.getPassword().getBytes()));
        player.setNickname(registerDTO.getNickname());
        playerRepository.save(player);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Valeu");
    }
    public ResponseEntity loginPlayer(LoginDto loginDto){
        Optional<Player> player = playerRepository.findByNicknameAndPassword(loginDto.getNickname(), base64.encodeAsString(
                loginDto.getPassword().getBytes()
        ));
        if (player.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro ao logar no sistema, cheque as informações");
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(player.get() + "Login realizado com sucesso");
    }
}
