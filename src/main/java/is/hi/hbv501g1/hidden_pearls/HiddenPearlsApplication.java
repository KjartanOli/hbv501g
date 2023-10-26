package is.hi.hbv501g1.hidden_pearls;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

// We only want spring-security for the PasswordEncoder classes all of
// its default configuration needs to kindly go away.
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class HiddenPearlsApplication {

	public static void main(String[] args) {
		SpringApplication.run(HiddenPearlsApplication.class, args);
	}
}
