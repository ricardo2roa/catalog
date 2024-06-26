package ws.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfig {
    private final JWTFilter jwtFilter;

    public SecurityConfig(JWTFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                //.securityMatcher("/v1/**")
            .csrf(AbstractHttpConfigurer::disable)
            .cors(cors -> cors.configurationSource(CorsConfig.corsConfigurationSource()))
            .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests((request) -> request
                    .requestMatchers(HttpMethod.POST,"/v1/api/auth/**").permitAll()
                    .requestMatchers(HttpMethod.GET,"/v1/producto/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.GET,"/v1/imagen/**").permitAll()
                    .requestMatchers(HttpMethod.POST,"/v1/imagen/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.GET, "/v1/productos").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.GET, "/v1/productos/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.GET,"/v1/producto/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.POST,"/v1/producto/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.PUT,"/v1/producto/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.POST, "/v1/brand/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/v1/brand/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.GET, "/v1/brands/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/v1/category/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.POST, "/v1/category/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.GET, "/v1/categories/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.POST, "/v1/tag/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.GET, "/v1/tags/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/v1/tag/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.POST, "/v1/reference").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.GET,"/v1/productos/ramdom").hasAuthority("ramdom")
                    .anyRequest().authenticated()
        )
        .addFilterBefore(this.jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
