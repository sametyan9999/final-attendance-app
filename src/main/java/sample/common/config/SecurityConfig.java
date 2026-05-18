package sample.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .authorizeHttpRequests(a -> a.anyRequest().permitAll())
            .csrf(c -> c.csrfTokenRepository(
                    CookieCsrfTokenRepository.withHttpOnlyFalse()
            ))
            .formLogin(f -> f.disable())
            .httpBasic(b -> b.disable());

        return http.build();
    }
}