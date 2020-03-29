import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
public class Example {

    @RequestMapping("/")
    String home() {
        return "Hello spring-boot!";
    }

    public static void main(String[] args) {
        // SpringApplication.run(Example.class, args);
        SpringApplication app = new SpringApplication(Example.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }

}