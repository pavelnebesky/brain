package eu.nebesky.brain.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void send(String from, String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        log.info("sending email message: {}", message);
        mailSender.send(message);
    }

    //@Scheduled(cron = "0 * * * * ?")
    private void sendScheduled () {
        send("brain@nebesky.eu","pavel@nebesky.eu", "Pozdrav cron", "Tohle je pozdrav");
    }
}
