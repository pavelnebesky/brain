package eu.nebesky.brain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class BrainApplication {

	public static void main(String[] args) {
		SpringApplication.run(BrainApplication.class, args);
	}


}
