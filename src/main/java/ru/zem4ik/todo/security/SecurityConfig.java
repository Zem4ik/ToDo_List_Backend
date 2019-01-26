
package ru.zem4ik.todo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private UserRepositoryUserDetailsService userDetailsService;
    @Autowired
    private Environment environment;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Customize the application security
        //todo can we make it better?
        for (String activeProfile : environment.getActiveProfiles()) {
            if (activeProfile.equals("prod")) {
                http.requiresChannel().anyRequest().requiresSecure();
            }
        }

        http
                .authorizeRequests()
                .antMatchers("/user_info", "/")
                .access("hasRole('ROLE_USER')")
                .antMatchers("/**").access("permitAll")


                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/user_info")


                .and()
                .logout()
                .logoutSuccessUrl("/")
        ;

        //todo: do this only for dev profile
        // add this line to use H2 web console
        // https://stackoverflow.com/questions/40165915/why-does-the-h2-console-in-spring-boot-show-a-blank-screen-after-logging-in
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(10);
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(encoder());

    }
}

