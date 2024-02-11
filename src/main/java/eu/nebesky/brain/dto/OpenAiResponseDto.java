package eu.nebesky.brain.dto;

import lombok.Data;

import java.util.List;

@Data
public class OpenAiResponseDto {
    private List<ChoiceDto> choices;
    private String model;
    private String id;
    private Integer created;
    private String system_fingerprint;
    private String object;
    private UsageDto usage;
}
