package eu.nebesky.brain.dto;

import lombok.Data;

import java.util.List;

@Data
public class OpenAiRequestDto {
    private String model;
    private List<OpenAiMessageDto> messages;
}
