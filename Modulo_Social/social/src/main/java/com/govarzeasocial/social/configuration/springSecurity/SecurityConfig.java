package com.govarzeasocial.social.configuration.springSecurity;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Profile("dev")
@EnableWebSecurity //Ativa a default filter chain + primário
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .csrf(customizer -> customizer.disable())
                .headers(headers -> headers.frameOptions(frame -> frame.disable())) //necessário para o H2 rodar
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll());

        return http.build();
    }

    //Necessário para o Filtro não interromper o aplicativo
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
