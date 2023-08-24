package ar.edu.unlp.cespi.sistemaDeEstacionamiento.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import ar.edu.unlp.cespi.sistemaDeEstacionamiento.config.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.Arrays;



@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringSecurityConfiguration {
	
	 private final JwtAuthenticationFilter jwtAuthenticationFilter;
	 private final AuthenticationProvider authProvider;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				.cors(cors -> {})
	            .csrf(csrf -> 
	                csrf
	                .disable())
	            .authorizeHttpRequests(authRequest ->
	              authRequest
	                .requestMatchers("/auth/**").permitAll()
	                .anyRequest().authenticated()
	                )
	            .sessionManagement(sessionManager->
	                sessionManager 
	                  .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	            .authenticationProvider(authProvider)
	            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
	            .build();
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
	    CorsConfiguration configuration = new CorsConfiguration();
	    configuration.setAllowedOrigins(Arrays.asList("*"));
	    configuration.setAllowedMethods(Arrays.asList("*"));
	    configuration.setAllowedHeaders(Arrays.asList("*"));
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", configuration);
	    return source;
	}
}
