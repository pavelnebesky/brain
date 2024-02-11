package eu.nebesky.brain.controllers;

import eu.nebesky.brain.dto.AiMessageDto;
import eu.nebesky.brain.dto.OpenAiResponseDto;
import eu.nebesky.brain.services.OpenAiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AiController {

    @Autowired
    OpenAiService openAiService;

    @PostMapping("/send-ai-request")
    AiMessageDto sendEmail(@RequestBody AiMessageDto aiMessageDto) {
        OpenAiResponseDto aiResponse = openAiService.getAiResponse(aiMessageDto.getRequest());
        AiMessageDto response = new AiMessageDto();
        response.setResponse(aiResponse.getChoices().get(0).getMessage().getContent());
        response.setRequest(aiMessageDto.getRequest());
        return response;
    }
    
}
