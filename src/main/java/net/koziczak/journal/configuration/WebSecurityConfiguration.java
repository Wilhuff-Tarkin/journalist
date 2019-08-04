package net.koziczak.journal.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // adresy wymagające logowania
                .antMatchers("/entry").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")

                // pozostałe nie wymagają logowania
                .anyRequest().permitAll()
                // wstrzyknięcie podstawowego formularza logowania
                .and()
                    .csrf().disable()
                    .formLogin()
                        // adres formularza logowania
                        .loginPage("/login")
                        // nazwy zmiennych z formularza (th:name)
                        .usernameParameter("email")
                        .passwordParameter("password")
                        // adres przekazania wartości po wysłaniu formularza
                        // th:action
                        .loginProcessingUrl("/login-process")
                        // przekierowanie po zalogowaniu
                        .defaultSuccessUrl("/entry")
                .and()
                    .logout()
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/");
    }
    @Autowired
    DataSource dataSource;
    @Autowired
    PasswordEncoder passwordEncoder;

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                // zapytanie zwaracjące logowanego użytkownika
                .usersByUsernameQuery(
                        "SELECT e.email, e.password, e.is_activated FROM users e WHERE e.email = ?")
                // zapytanie zwracające rolę logowanego użytkownika
                .authoritiesByUsernameQuery(
                        "SELECT e.email, r.role_name FROM users e " +
                                "JOIN user_role er ON e.user_id = er.user_id " +
                                "JOIN role r ON r.role_id = er.role_id " +
                                "WHERE e.email = ?")
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder);
    }
}
