package eu.nebesky.brain.dto;

import lombok.Data;

@Data
public class UsageDto {
    private Integer completion_tokens;
    private Integer prompt_tokens;
    private Integer total_tokens;
}
