package site.dadangsinhhoc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class DadangsinhhocApplication {

    public static void main(String[] args) {
        SpringApplication.run(DadangsinhhocApplication.class, args);

        log.info("Start project with: http://localhost:8080/login");
        log.info("Swagger UI in: http://localhost:8080/swagger-ui/index.html#/");
        log.info("Client in: http://localhost:3000");
    }

}
