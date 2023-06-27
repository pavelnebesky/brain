package eu.nebesky.brain.controllers;

import eu.nebesky.brain.dto.EmailRequestDto;
import eu.nebesky.brain.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    EmailService emailService;

    @PostMapping("/send-email")
    void sendEmail(@RequestBody EmailRequestDto emailRequestDto) {
        emailService.send(
                emailRequestDto.getFrom(),
                emailRequestDto.getTo(),
                emailRequestDto.getSubject(),
                emailRequestDto.getText());
    }

}
