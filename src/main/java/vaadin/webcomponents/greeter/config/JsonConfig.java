package vaadin.webcomponents.greeter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import elemental.json.impl.JreJsonFactory;

/**
 * JsonConfig
 */
@Configuration
public class JsonConfig {

    @Bean
    public JreJsonFactory json(){
        return new JreJsonFactory();
    }
    
}