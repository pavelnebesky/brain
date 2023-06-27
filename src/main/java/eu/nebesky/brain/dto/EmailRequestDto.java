package eu.nebesky.brain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmailRequestDto {
    private String from;
    private String to;
    private String subject;
    private String text;
}
