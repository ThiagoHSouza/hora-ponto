package br.com.developer.horaponto;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

import br.com.developer.horaponto.core.domain.repository.TimePointRepository;

@EnableMongoAuditing
@SpringBootApplication
public class HoraPontoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HoraPontoApplication.class, args);
	}
	
	@Bean
	CommandLineRunner run(TimePointRepository timePointRepository){
		return (evt) -> {
			timePointRepository.deleteAll();
		};
	}
}
