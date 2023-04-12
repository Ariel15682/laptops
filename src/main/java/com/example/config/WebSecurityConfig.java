package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter { //deprecated luego de Spring Boot 2.5.5


    protected void configure(HttpSecurity http) throws Exception {
        // super.configure(http); esto que se pega no hace falta porque queremos sobrescribir
        http.authorizeRequests()
        .antMatchers("/saludo").permitAll() // esta permitida sin auth, para las demas hay que loguearse
        .antMatchers("/api/delete-all").hasRole("ADMIN")
        //.antMatchers("").hasAnyAuthority()
        //.antMatchers("").hasAnyRole()
        .anyRequest().authenticated()
        .and()
        .formLogin()
        .and()
        .httpBasic(); // method chaining concatenacion de metodo: obj.metodo1().metodo2()
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // enable in memory based authentication with a user named

        // user and admin
        auth.inMemoryAuthentication()
        .passwordEncoder(passwordEncoder())
        .withUser("user").password(passwordEncoder().encode("1234")).roles("USER")
        .and()
        .withUser("admin").password(passwordEncoder().encode("12345")).roles("USER", "ADMIN");

        // otro user ADMIN
        //.and()
        //.withUser("admin").password("password").roles("USER");
    }

    /*
    // Metodo configuracion de Firewall que permite enviar ";" (semicolon) dentro de la peticion al endpoint (ej.: /;saludo)
    @Bean
    public HttpFirewall looseHttpFirewall(){
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowSemicolon(true); //permite punto y coma
        return firewall;
    }
    */
}
