package fr.intellcap.artproject.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.cors().and().csrf().disable()
				.authorizeRequests()
				.antMatchers("/api/authentication/**", "/api/accounts/**", "/api/token/**", "/api/accounts/register",
						"/v2/api-docs", "/gammes/**", "/groupes/**", "/voitures/**", "/reservation/**", "/options/**",
						"/clients/**", "/activites/**", "/configuration/ui", "/swagger-resources/**",
						"/configuration/security", "/swagger-ui/**")
				.permitAll().anyRequest().permitAll();
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOriginPatterns(Arrays.asList("*")); // Use allowedOriginPatterns
		configuration.setAllowedHeaders(Arrays.asList(
				"Access-Control-Allow-Headers",
				"Access-Control-Allow-Origin",
				"Access-Control-Request-Method",
				"Access-Control-Request-Headers",
				"Origin",
				"Cache-Control",
				"Content-Type",
				"Authorization"
		));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
		configuration.setAllowCredentials(true);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);

		return source;
	}



}
