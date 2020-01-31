package team47pack;

import java.text.ParseException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableScheduling
@EnableTransactionManagement


public class Team47ProjectApplication {

	public static void main(String[] args) throws ParseException {
		SpringApplication.run(Team47ProjectApplication.class, args);

	}

}
