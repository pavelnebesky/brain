package eu.nebesky.brain.dto;

import lombok.Data;

@Data
public class OpenAiMessageDto {
    private Role role;
    private String content;
}
