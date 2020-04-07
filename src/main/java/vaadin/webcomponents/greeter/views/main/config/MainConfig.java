package vaadin.webcomponents.greeter.views.main.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import vaadin.webcomponents.greeter.views.main.MenuProperties;

/**
 * MainConfig
 */
@Configuration
@EnableConfigurationProperties(MenuProperties.class)
public class MainConfig {

}