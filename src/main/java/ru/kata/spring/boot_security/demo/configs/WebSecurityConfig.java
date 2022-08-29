package ru.kata.spring.boot_security.demo.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kata.spring.boot_security.demo.service.UserDetailService;

@Configuration
@EnableWebSecurity
// по этой аннотации Спринг понимает, что это конфиг класс для Spring Security
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final SuccessUserHandler successUserHandler;

    private final LoginSuccessHandler loginSuccessHandler;

    private final UserDetailService userDetailService;

    public WebSecurityConfig(SuccessUserHandler successUserHandler, UserDetailService userDetailService,
                             LoginSuccessHandler loginSuccessHandler) {
        this.successUserHandler = successUserHandler;
        this.userDetailService = userDetailService;
        this.loginSuccessHandler=loginSuccessHandler;
    }

    // настраиваем аутентификацию:
    protected void config(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {                        // метод конфигурирует сам Spring security (какая страница отвечает за вход, а какая за ошибки и т.д.)
        http.csrf().disable()                                                             // также в этом методе конфигурируем авторизацию
                .authorizeRequests()                                                      // вызов метода означает, что все постутпающие запросы должны проходить авторизацию
                .antMatchers("/admin").hasRole("ADMIN")                         // даем доступ к странице admin только тем, у кого роли ADMIN
                .antMatchers("/", "/index", "/api/create").permitAll()          // устанавливаем end points, с которых должен быть доступен всем без пароля
                .anyRequest().hasAnyRole("USER", "ADMIN")                           //  даем доступ ко всем остальным страницам как USer-у, так и админу
                .and()                                                                    // до end() настраивается авторизация, после end() настраивается страница login
                .formLogin().successHandler(successUserHandler)                           // настраиваем свою форму для пользователя
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")                                                     // logout - из сессии удаляется пользователь + у пользователя удаляются cookies
                .logoutSuccessUrl("/auth/login");                                         // в случае успешного logout пользователь будет переведен на страницу auth/login
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {                                           // показываем Spring Security с помощью какого алгоритма шифруем пароли
        return NoOpPasswordEncoder.getInstance();                                           // сейчас пока пароль не шифруем
    }
}