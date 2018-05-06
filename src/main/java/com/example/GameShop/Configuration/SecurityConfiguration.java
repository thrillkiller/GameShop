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
    auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery("select username,password, 'true' as enabled from user where  username=?").
            authoritiesByUsernameQuery("select username,'any' as role from user where supervisor = true and username=?").
            passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
    http.authorizeRequests().antMatchers("/","/games","/other","/game/**","product/**","/users").permitAll()
            .anyRequest().authenticated();
    http.authorizeRequests().antMatchers("/addNew","/orders","/settings").hasAnyAuthority();
    http.formLogin().loginPage("/login").usernameParameter("username").passwordParameter("password").permitAll()
            .successHandler(succesfullAuthenticationHandler).failureUrl("/login/error");
    http.logout().logoutUrl("/logout").logoutSuccessUrl("/").and().exceptionHandling().accessDeniedPage("/403");
    http.csrf().disable();
    }

    public void setSuccesfullAuthenticationHandler(SuccesfullAuthenticationHandler succesfullAuthenticationHandler) {
       this.succesfullAuthenticationHandler = succesfullAuthenticationHandler;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
