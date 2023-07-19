package eu.nebesky.brain.dto;

import lombok.Data;

@Data
public class EmailRequestDto {
    private String from;
    private String to;
    private String subject;
    private String text;
}
