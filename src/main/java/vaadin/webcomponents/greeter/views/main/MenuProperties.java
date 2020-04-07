package vaadin.webcomponents.greeter.views.main;

import java.util.Map;
import java.util.TreeMap;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * MenuProperties
 */
@Data
@ConfigurationProperties(prefix = "menu")
public class MenuProperties {
    private Map<String, App> apps = new TreeMap<>();
    private Logo logo;

    @Data
    public static class App {
        private String url;
        private String requiredRole;
    }

    @Data
    public static class Logo {
        private String url;
        private String alt;
    }

}