package eu.nebesky.brain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.math.BigDecimal;
import java.text.DecimalFormat;

@EnableScheduling
@EnableAsync
@SpringBootApplication
public class BrainApplication {

	public static void main(String[] args) {
		SpringApplication.run(BrainApplication.class, args);
	}


}
