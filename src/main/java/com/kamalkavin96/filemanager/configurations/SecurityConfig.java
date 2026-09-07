package com.kamalkavin96.filemanager.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// import org.springframework.security.crypto.factory.PasswordEncoderFactories;
// import org.springframework.security.crypto.password.PasswordEncoder;

// @Configuration
// @EnableWebSecurity
public class SecurityConfig {

    // @Bean
    // public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity){
    //     httpSecurity
    //         .csrf(csrf->csrf.disable())
    //         .authorizeHttpRequests(auth->auth.anyRequest().hasAuthority("ROLE_USER"))
    //         .httpBasic(basic->basic.realmName("basic realm"))
    //         .build();
    //     return httpSecurity.build();
    // }

    // @Bean
    // public PasswordEncoder passwordEncoder(){
    //     return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    // }

    // @Bean
    // public UserDetailsService userDetailsService(){
    //     UserDetails userDetails = User.builder()
    //         .username("user")
    //         .password("{bcrypt}$2a$10$FMzmOkkfbApEWxS.4XzCKOR7EbbiwzkPEyGgYh6uQiPxurkpzRMa6")
    //         .authorities("ROLE_USER")
    //         .build();
    //     return new InMemoryUserDetailsManager(userDetails);
    // }
}
