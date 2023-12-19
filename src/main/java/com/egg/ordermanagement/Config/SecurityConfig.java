package com.egg.ordermanagement.Config;

import com.egg.ordermanagement.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.firewall.StrictHttpFirewall;

import static org.springframework.security.config.Customizer.withDefaults;


//@EnableMethodSecurity(prePostEnabled = true)
/*The rationale behind this change is that with @EnableMethodSecurity
property prePostEnabled needed to enable use of @PreAuthorize/@PostAuthorize
and @PreFilter/@PostFilter is by default set to true.*/

/* public class SecurityConfig extends WebSecurityConfigureAdapter
Deprecated
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
@RequiredArgsConstructor
public class SecurityConfig {


    @Autowired
    public CustomerService customerService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customerService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean //Equivalente a configure, request equivalente a antMatchers
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/js/**", "/images/**");
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http

                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth ->
                        auth

                                //.requestMatchers("/admin/*").hasRole("ADMIN")
                                .requestMatchers("/css/**", "/js/**").permitAll()
                                .requestMatchers("/register").permitAll()
                                .requestMatchers("/login").permitAll()
                                .anyRequest().authenticated()

                )
                .formLogin( loginConfig ->
                        loginConfig
                                //.loginPage("/login")
                                //.loginProcessingUrl("/login")
                                .usernameParameter("email")
                                .passwordParameter("password")
                                .defaultSuccessUrl("/index",true)
                                .permitAll()
                )
                .logout( logoutConfig ->
                        logoutConfig
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/login")
                                .permitAll()
                )
                .build();

    }


}
