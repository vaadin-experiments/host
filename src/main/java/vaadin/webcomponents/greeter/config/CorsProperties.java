package vaadin.webcomponents.greeter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@ConfigurationProperties("cors")
@Data
public class CorsProperties {
	private String allowedOrigin;
	private String allowedMethod;
	private String allowedHeader;
	private Boolean allowCredentials;
}
