package abdkzmv.wm2.assignment2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class BeanConfig {

    @Bean
    public String greetText() {
        return "Welcome!";
    }

    @Bean
    public String byeText() {
        return "See you!";
    }

}
