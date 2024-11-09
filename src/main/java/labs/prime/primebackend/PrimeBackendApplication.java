package labs.prime.primebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class PrimeBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrimeBackendApplication.class, args);
    }

}
