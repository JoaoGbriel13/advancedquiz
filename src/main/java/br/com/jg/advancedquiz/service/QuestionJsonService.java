package br.com.jg.advancedquiz.service;

import br.com.jg.advancedquiz.dto.JsonQuestionDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Service
public class QuestionJsonService {
    protected List<JsonQuestionDTO> getFromApi() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("https://opentdb.com/api.php?amount=50&type=multiple",
                String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode results = mapper.readTree(responseEntity.getBody());
        JsonQuestionDTO questionObject = mapper.readValue(results.toString(), JsonQuestionDTO.class);
        return questionObject.getResults();
    }
}
