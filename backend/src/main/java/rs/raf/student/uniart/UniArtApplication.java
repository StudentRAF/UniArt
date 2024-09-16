package rs.raf.student.uniart;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import rs.raf.student.uniart.exception.UniArtException;

@EnableJpaAuditing
@SpringBootApplication
public class UniArtApplication {

	public static void main(String[] args) {
		UniArtException.setLogger(LoggerFactory.getLogger(UniArtApplication.class));
		SpringApplication.run(UniArtApplication.class, args);
	}

}
