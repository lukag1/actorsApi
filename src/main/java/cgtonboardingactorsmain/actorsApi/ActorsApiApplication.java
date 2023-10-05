package cgtonboardingactorsmain.actorsApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class ActorsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActorsApiApplication.class, args);
	}

}
