package com.example.taskmanager.config;


import com.example.taskmanager.security.CustomUserDetailsService;
import com.example.taskmanager.security.JwtFilter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
    private final CustomUserDetailsService
            customUserDetailsService;
    private final JwtFilter jwtFilter;

    @Bean
    SecurityFilterChain filterChain(
            HttpSecurity http
    ) throws Exception{


        return http

                .csrf(

                        AbstractHttpConfigurer::disable

                )

                .authorizeHttpRequests(

                        auth->


                                auth


                                        .requestMatchers(

                                                "/auth/**"

                                        )


                                        .permitAll()



                                        .requestMatchers(

                                                "/swagger-ui/**",

                                                "/v3/api-docs/**",
                                                "/api-docs/**"

                                        )


                                        .permitAll()




                                        .anyRequest()


                                        .authenticated()

                )

                .sessionManagement(

                        session->


                                session.sessionCreationPolicy(

                                        SessionCreationPolicy.STATELESS

                                )

                )



                .authenticationProvider(

                        authenticationProvider()

                )



                .addFilterBefore(

                        jwtFilter,


                        UsernamePasswordAuthenticationFilter.class

                )



                .build();




    }
    @Bean
    AuthenticationProvider authenticationProvider(){


        DaoAuthenticationProvider provider=


                new DaoAuthenticationProvider(customUserDetailsService);







        provider.setPasswordEncoder(

                passwordEncoder()

        );



        return provider;


    }
    @Bean
    PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();

    }
    @Bean
    UserDetailsService userDetailsService(){

        return customUserDetailsService;

    }
    @Bean
    AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration
    ) throws Exception {

        return configuration.getAuthenticationManager();

    }
}
