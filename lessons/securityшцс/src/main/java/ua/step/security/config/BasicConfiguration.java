package ua.step.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class BasicConfiguration {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager(PasswordEncoder encoder) {
        UserDetails user = User
                .withUsername("user")
                .password(encoder.encode( "user"))
                .roles("USER").build();
        UserDetails admin = User
                .withUsername("admin")
                .password(encoder.encode( "admin"))
                .roles("MODERATOR","ADMIN").build();
        UserDetails moderator = User
                .withUsername("moderator")
                .password(encoder.encode( "moderator"))
                .roles("MODERATOR", "USER").build();

        return new InMemoryUserDetailsManager(user, moderator, admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests()
                .antMatchers("/user/**").hasAnyRole("USER", "MODERATOR", "ADMIN")
                .antMatchers("/moderator/**").hasAnyRole("MODERATOR", "ADMIN")
                .antMatchers("/admin/**").hasAnyRole( "ADMIN")
                .antMatchers(HttpMethod.GET, "/").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .permitAll()
                .and()
                .httpBasic();
        return httpSecurity.build();
    }
}
