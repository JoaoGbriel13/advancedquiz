package br.com.jg.advancedquiz.mapper;

import br.com.jg.advancedquiz.dto.PlayerDTO;
import br.com.jg.advancedquiz.model.Player;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PlayerMapper {
    private final ModelMapper mapper;

    public PlayerMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }
    public Player toEntity(PlayerDTO playerDTO){
        return mapper.map(playerDTO, Player.class);
    }
    public PlayerDTO toDTO(Player player){
        return mapper.map(player, PlayerDTO.class);
    }
    public List<PlayerDTO> toListDTO(List<Player> players){
        return players.stream().map((element) -> mapper.map(element, PlayerDTO.class)).collect(Collectors.toList());
    }
}
