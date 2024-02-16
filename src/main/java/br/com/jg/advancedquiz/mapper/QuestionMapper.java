package br.com.jg.advancedquiz.mapper;

import br.com.jg.advancedquiz.dto.QuestionDTO;
import br.com.jg.advancedquiz.model.Question;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuestionMapper {
    private final ModelMapper mapper;

    public QuestionMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }
    public Question toEntity(QuestionDTO questionDTO){
        return mapper.map(questionDTO, Question.class);
    }
    public QuestionDTO questionDTO(Question question){
        return mapper.map(question, QuestionDTO.class);
    }

    public List<QuestionDTO> questionDTOS(List<Question> questions){
        return questions.stream().map((element) -> mapper.map(element, QuestionDTO.class)).collect(Collectors.toList());
    }
}
