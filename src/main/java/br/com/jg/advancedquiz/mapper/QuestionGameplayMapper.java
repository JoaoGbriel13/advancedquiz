package br.com.jg.advancedquiz.mapper;

import br.com.jg.advancedquiz.dto.QuestionGameplayDTO;
import br.com.jg.advancedquiz.model.QuestionGameplay;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuestionGameplayMapper {
    private final ModelMapper mapper;

    public QuestionGameplayMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }
    public QuestionGameplay toEntity(QuestionGameplayDTO questionGameplayDTO){
        return mapper.map(questionGameplayDTO, QuestionGameplay.class);
    }
    public QuestionGameplayDTO toDTO(QuestionGameplay questionGameplay){
        return mapper.map(questionGameplay, QuestionGameplayDTO.class);
    }
    public List<QuestionGameplayDTO> questionGameplayDTOList(List<QuestionGameplay> questionGameplayList){
        return questionGameplayList.stream().map((element) -> mapper.map(element, QuestionGameplayDTO.class)).collect(Collectors.toList());
    }
}
