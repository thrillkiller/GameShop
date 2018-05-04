package com.example.GameShop.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

    @Autowired
    SuccesfullAuthenticationHandler succesfullAuthenticationHandler;

    @Autowired
    DataSource dataSource;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception{
    auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery("select login,password, 'true' as enabled from user where supervisor = false and login=?").
            authoritiesByUsernameQuery("select username,password 'true' as enabled from user where supervisor = false and login=?").
            passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
    http.authorizeRequests().antMatchers("/","/games","/other","/game/**","product/**").permitAll()
            .anyRequest().authenticated();
    http.authorizeRequests().antMatchers("/addNew","/orders","/settings","/users").hasAnyAuthority();
    http.formLogin().loginPage("/login").usernameParameter("login").passwordParameter("password").permitAll()
            .successHandler(succesfullAuthenticationHandler).failureUrl("/login/error");
    http.logout().logoutUrl("/logout").logoutSuccessUrl("/").and().exceptionHandling().accessDeniedPage("/403");
    http.csrf().disable();
    }

    public void setSuccesfullAuthenticationHandler(SuccesfullAuthenticationHandler succesfullAuthenticationHandler) {
       this.succesfullAuthenticationHandler = succesfullAuthenticationHandler;
    }
}
