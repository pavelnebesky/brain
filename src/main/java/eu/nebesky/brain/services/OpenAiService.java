package eu.nebesky.brain.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.nebesky.brain.dto.OpenAiMessageDto;
import eu.nebesky.brain.dto.OpenAiRequestDto;
import eu.nebesky.brain.dto.OpenAiResponseDto;
import eu.nebesky.brain.dto.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Scanner;


@Slf4j
@Service
public class OpenAiService {

    HttpClient client = HttpClient.newHttpClient();
    ObjectMapper objectMapper = new ObjectMapper();
    @Value("${openai.api.key}")
    private String openAiApiKey;

    public OpenAiResponseDto getAiResponse(String question) {

        OpenAiResponseDto openAiResponseDto = null;
        try {
            String cvPrompt = new Scanner(getClass().getClassLoader().getResourceAsStream("cvPrompt.txt"), "UTF-8").useDelimiter("\\A").next();

            OpenAiMessageDto systemMessage = new OpenAiMessageDto();
            systemMessage.setRole(Role.system);
            systemMessage.setContent(cvPrompt);

            OpenAiMessageDto userMessage = new OpenAiMessageDto();
            userMessage.setRole(Role.user);
            userMessage.setContent(question);

            OpenAiRequestDto openAiRequestDto = new OpenAiRequestDto();
            openAiRequestDto.setModel("gpt-3.5-turbo");
            openAiRequestDto.setMessages(List.of(systemMessage, userMessage));

            String requestBody = objectMapper.writeValueAsString(openAiRequestDto);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("https://api.openai.com/v1/chat/completions"))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + openAiApiKey)
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            openAiResponseDto = objectMapper.readValue(response.body(), OpenAiResponseDto.class);

            System.out.println(response.body());
        } catch (Exception e) {
            log.error("Failed due to error {}", e.getMessage(), e);
        }

        return openAiResponseDto;
    }


}
