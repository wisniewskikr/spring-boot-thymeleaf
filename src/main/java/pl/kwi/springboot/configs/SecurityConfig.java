package pl.kwi.springboot.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import pl.kwi.springboot.handlers.CustomAuthenticationFailureHandler;
import pl.kwi.springboot.handlers.CustomAuthenticationSuccessHandler;
import pl.kwi.springboot.providers.CustomAuthenticationProvider;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
    CustomAuthenticationProvider customAuthProvider;
	
	@Autowired
	CustomAuthenticationSuccessHandler customSuccessHandler;
	
	@Autowired
	CustomAuthenticationFailureHandler customFailureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
        	.authorizeRequests()
        		.antMatchers("/login").permitAll()
        		.anyRequest().authenticated()	        		
            .and()
            .formLogin()
				.loginPage("/login")
				.successHandler(customSuccessHandler)
				.failureHandler(customFailureHandler)
				.permitAll()
			.and()
            .logout()
				.permitAll();
        
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	auth.authenticationProvider(customAuthProvider);        
    }
    
}
