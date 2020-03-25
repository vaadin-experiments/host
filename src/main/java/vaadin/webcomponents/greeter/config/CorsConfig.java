package vaadin.webcomponents.greeter.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import lombok.AllArgsConstructor;
/**
 *	Allows embedding of the web-component. 
 */
@Configuration
@EnableConfigurationProperties(CorsProperties.class)
@AllArgsConstructor
public class CorsConfig  {

	private CorsProperties props;
	
	/**
	 * see:
	 * https://spring.io/blog/2015/06/08/cors-support-in-spring-framework.
	 */
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter() {
		var config = createCorsConfiguration();
		var source = new UrlBasedCorsConfigurationSource();
		var corsFilter = new CorsFilter(source);
		var bean = new FilterRegistrationBean<CorsFilter>(corsFilter);
		source.registerCorsConfiguration("/**", config);
		bean.setOrder(0);
		return bean;
	}

	private CorsConfiguration createCorsConfiguration() {
		var config = new CorsConfiguration();
		
		if(props.getAllowCredentials() != null) {
			config.setAllowCredentials(true);
		}
		
		if(isNotBlank(props.getAllowedOrigin())) {
			config.addAllowedOrigin(props.getAllowedOrigin());
		}
		
		if(isNotBlank(props.getAllowedHeader())) {
			config.addAllowedHeader(props.getAllowedHeader());
		}

		if(isNotBlank(props.getAllowedMethod())) {
			config.addAllowedMethod(props.getAllowedHeader());
		}
		
		return config;
	}

}
