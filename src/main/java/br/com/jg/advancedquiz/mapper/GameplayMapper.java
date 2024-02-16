package br.com.jg.advancedquiz.mapper;

import br.com.jg.advancedquiz.dto.GameplayModelDTO;
import br.com.jg.advancedquiz.model.GameplayModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GameplayMapper {
    private final ModelMapper mapper;

    public GameplayMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }
    public GameplayModel toEntity(GameplayModelDTO gameplayModelDTO){
        return mapper.map(gameplayModelDTO, GameplayModel.class);
    }
    public GameplayModelDTO toDTO(GameplayModel gameplayModel){
        return mapper.map(gameplayModel, GameplayModelDTO.class);
    }

    public List<GameplayModelDTO> gameplayModelDTOS(List<GameplayModel> gameplayModels){
        return gameplayModels.stream().map((element) -> mapper.map(element, GameplayModelDTO.class)).collect(Collectors.toList());
    }
}
