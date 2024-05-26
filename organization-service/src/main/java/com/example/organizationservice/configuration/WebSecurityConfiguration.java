package com.example.organizationservice.configuration;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.springframework.web.cors.CorsConfiguration.ALL;

/**
 * @implNote <b>Web Security Configuration</b>
 */
@Configuration
public class WebSecurityConfiguration {

    private final ApplicationProperties applicationProperties;

    List<String> allowedOriginURLs;

    private Long maxAge;

    public WebSecurityConfiguration(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    @PostConstruct
    private void init() {
        maxAge = applicationProperties.getMaxAge();
        allowedOriginURLs = applicationProperties.getAllowedOriginURLs();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedMethods(Arrays.asList(
                HttpMethod.GET.name(),
                HttpMethod.POST.name(),
                HttpMethod.DELETE.name(),
                HttpMethod.PUT.name()
        ));
        configuration.setAllowedHeaders(Collections.singletonList(ALL));
        configuration.setExposedHeaders(Collections.singletonList(ALL));
        configuration.setAllowedOrigins(allowedOriginURLs);
        configuration.setMaxAge(maxAge);
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//        http.csrf(csrf -> csrf.disable())
//                .cors()
//                .and()
//                .exceptionHandling(exception ->
//                        exception.authenticationEntryPoint(unauthorizedHandler))
//                .sessionManagement(session ->
//                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authorizeHttpRequests(auth -> auth
//                        .antMatchers("/v3/api-docs/**").permitAll()
//                        .antMatchers("/swagger-ui/**").permitAll()
//                        .antMatchers("/v1/**").permitAll());
//        // .antMatchers("/v1/**").authenticated());
//        return http.build();
//    }
}
