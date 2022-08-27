package ru.kata.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import ru.kata.spring.boot_security.demo.security.AuthProviderImpl;

@Configuration
@EnableWebSecurity
// по этой аннотации Спринг понимает, что это конфиг класс для Spring Security
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {   // почему зачеркнуто? а Алишева это есть

    private final SuccessUserHandler successUserHandler;

    private final AuthenticationProvider authProvider;

    public WebSecurityConfig(SuccessUserHandler successUserHandler, AuthProviderImpl authProvider) {
        this.successUserHandler = successUserHandler;
        this.authProvider = authProvider;
    }

    // этот метод добавила я (по Алишеву):
    protected void config(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {   // этот метод настраивает аутентификацию
        http
                .authorizeRequests()
                .antMatchers("/", "/index", "/api/create").permitAll()   // endpoint create должен быть доступен всем без пароля
                .anyRequest().authenticated()
                .and()
                .formLogin().successHandler(successUserHandler)
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }
}