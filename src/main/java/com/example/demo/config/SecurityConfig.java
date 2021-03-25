package com.example.demo.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {



    @Autowired
    private DataSource dataSource;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username, password, enabled from user where username=?")
                .authoritiesByUsernameQuery("select login, role from role where login=?");



    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/manageUsers/users","/manageUsers/addUser","/manageUsers/editUser/{id}","/manageUsers/deleteUser","/manageConcerts/concerts","/manageConcerts/addConcert","/manageConcerts/editConcert","/manageConcert/deleteConcert","/bills/csv","bills/json")
                .hasAnyRole("ADMIN")
                .antMatchers("/manageTickets/showSoldTickets","/showSoldTickets/sellTicket","/manageTickets/cancelTicket","/manageTickets/editTicket")
                .hasAnyRole("CASHIER")
                .and()
                .csrf().disable()
                .formLogin().disable();



    }
}
