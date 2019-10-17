package com.shakti.developer.demo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       
    auth.inMemoryAuthentication().withUser("John").password("Doe").roles("USER");
    	
    }
    
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**").
        antMatchers(HttpMethod.DELETE, "/**").
        antMatchers(HttpMethod.PUT, "/**").
        antMatchers(HttpMethod.POST, "/**").
        antMatchers(HttpMethod.GET, "/**");
        
    }
    
    @Bean
    public PasswordEncoder getPasswordEncoder() {
    	return NoOpPasswordEncoder.getInstance();
    }
    
    
}
