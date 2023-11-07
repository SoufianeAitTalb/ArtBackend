package fr.intellcap.artproject.config;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

import javax.crypto.spec.SecretKeySpec;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
//extends WebSecurityConfigurerAdapter
public class WebSecurityConfig  {


//	@Override
//	protected void configure(HttpSecurity httpSecurity) throws Exception {
//		httpSecurity.cors().and().csrf().disable()
//				.authorizeRequests()
//				.antMatchers("/api/authentication/**", "/api/accounts/**", "/api/token/**", "/api/accounts/register",
//						"/v2/api-docs", "/gammes/**", "/groupes/**", "/voitures/**", "/reservation/**", "/options/**",
//						"/clients/**", "/activites/**", "/configuration/ui", "/swagger-resources/**",
//						"/configuration/security", "/swagger-ui/**")
//				.permitAll().anyRequest().permitAll();
//	}
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws  Exception{
//
//
//	return httpSecurity.build();
//	}
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws  Exception {

		return httpSecurity
				.sessionManagement(sm->sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//				.authorizeHttpRequests(ar -> ar.requestMatchers().permitAll())
				.authorizeHttpRequests(ar-> ar.anyRequest().permitAll())
				.csrf(csrf -> csrf.disable())
				.cors(Customizer.withDefaults())
				.oauth2ResourceServer(oa->oa.jwt(Customizer.withDefaults()))

				.build();
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOriginPatterns(Arrays.asList("*")); // Use allowedOriginPatterns
		configuration.setAllowedHeaders(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("*"));
		configuration.setAllowCredentials(true);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);

		return source;
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


	@Bean
	InMemoryUserDetailsManager inMemoryUserDetailsManager(){
		PasswordEncoder passwordEncoder=passwordEncoder();

	return new InMemoryUserDetailsManager(
			User.withUsername("user").password(passwordEncoder.encode("user")).authorities("USER").build(),
			User.withUsername("admin").password(passwordEncoder.encode("admin")).authorities("USER","ADMIN").build()

	) ;
	}

	@Bean
	JwtEncoder jwtEncoder(){
		String secretKey="29a73fe8655bac6d08491daffcb090fc7021aff491983906cabd5d0e8ff5bf00";
return new NimbusJwtEncoder(new ImmutableSecret<>(secretKey.getBytes()));
	}
	@Bean
	JwtDecoder jwtDecoder(){
		String secretKey="29a73fe8655bac6d08491daffcb090fc7021aff491983906cabd5d0e8ff5bf00";
		SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(),"RSA");
		return NimbusJwtDecoder.withSecretKey(secretKeySpec).macAlgorithm(MacAlgorithm.HS512).build();
	}

	@Bean
	public AuthenticationManager authenticationManager(UserDetailsService userDetailsService){
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		daoAuthenticationProvider.setUserDetailsService(userDetailsService);
		return new ProviderManager(daoAuthenticationProvider);
	}



}
