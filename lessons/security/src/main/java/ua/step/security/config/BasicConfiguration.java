package ua.step.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ua.step.security.model.UserRole;
import ua.step.security.repository.UserRoleRepository;
import ua.step.security.service.UserService;

import java.util.Collections;

@Configuration
@EnableWebSecurity
public class BasicConfiguration {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleRepository userRoleRepository;


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
    public CommandLineRunner initDb(ApplicationContext context) {
        return args -> {

            UserRole adminRole = new  UserRole("ADMIN");
            userRoleRepository.save(adminRole);

            ua.step.security.model.User user = new ua.step.security.model.User(
                    "admin",
                    passwordEncoder().encode("admin"),
                    Collections.singletonList(adminRole)
            );
            userService.save(user);
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests()
                .antMatchers("/user/**").hasAnyRole("USER", "MODERATOR", "ADMIN")
                .antMatchers("/moderator/**").hasAnyRole("MODERATOR", "ADMIN")
                .antMatchers("/admin/**").hasAnyRole( "ADMIN")
                .antMatchers(HttpMethod.GET, "/").permitAll()
                .antMatchers("/signup").permitAll()
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

        httpSecurity.csrf().ignoringAntMatchers("/h2-console/**");
        httpSecurity.headers().frameOptions().sameOrigin();

        return httpSecurity.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
