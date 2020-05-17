package co.lilpilot.postcard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class PostcardApplication {

    public static void main(String[] args) {
        SpringApplication.run(PostcardApplication.class, args);
    }

}
