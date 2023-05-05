package rk.security3.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import rk.security3.model.UserRole;
import rk.security3.repository.UserRoleRepository;
import rk.security3.service.UserService;

import java.util.Collections;

@Configuration
@EnableWebSecurity
public class BasicConfiguration {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Bean
    public UserDetailsService userDetailsManager(PasswordEncoder encoder) {
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

           rk.security3.model.User user = new rk.security3.model.User(
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
                .authorizeHttpRequests(request -> { request
                        .requestMatchers("/", "/signup").permitAll()
                        .anyRequest().authenticated();


//                    request.requestMatchers("/user/**").hasAnyRole("USER", "MODERATOR", "ADMIN");
//                    request.requestMatchers("/moderator/**").hasAnyRole( "MODERATOR", "ADMIN");
//                    request.requestMatchers("/admin/**").hasAnyRole( "ADMIN");
                }).formLogin(form -> {
                    form.loginPage("/login").permitAll();
                })
                //.antMatchers("/user/**").hasAnyRole("USER", "MODERATOR", "ADMIN")
                //.antMatchers("/moderator/**").hasAnyRole("MODERATOR", "ADMIN")
                //.antMatchers("/admin/**").hasAnyRole( "ADMIN")
                .logout(LogoutConfigurer::permitAll);
//                .logoutSuccessUrl("/login")
//                .deleteCookies("JSESSIONID")
//                .invalidateHttpSession(true)
//                .permitAll()
//                .and()
//                .httpBasic();

       // httpSecurity.csrf().ignoringAntMatchers("/h2-console/**");
       // httpSecurity.headers().frameOptions().sameOrigin();

        return httpSecurity.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
