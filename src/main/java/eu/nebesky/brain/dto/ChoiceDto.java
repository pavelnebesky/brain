package eu.nebesky.brain.dto;

import lombok.Data;

@Data
public class ChoiceDto {
    private OpenAiMessageDto message;
    private String finish_reason;
    private Integer index;
    private Object logprobs;
}
