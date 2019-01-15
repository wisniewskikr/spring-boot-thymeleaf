package pl.kwi.springboot.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class MultiSecurityConfig {
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("user").password("{noop}password").roles("USER")
                .and()
                .withUser("admin").password("{noop}password").roles("ADMIN");
        
    }
	
	@Configuration
	@Order(1)
	public static class BasicSecurityConfig extends WebSecurityConfigurerAdapter {
		
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	    	
	    	http
	    	.antMatcher("/api/**")
	    		.authorizeRequests()
	    			.antMatchers("/api/hello/**").hasRole("ADMIN")
	    		.and()
	        		.httpBasic()
	        	.and()
	        		.csrf().disable();
	        
	    }
	    
	}
	
	@Configuration
	@Order(2)
	public static class FormSecurityConfig extends WebSecurityConfigurerAdapter {
		
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	    	
	        http
	        	.authorizeRequests()        		
	        		.antMatchers("/css/**", "/js/**", "/images/**").permitAll()
	        		.antMatchers("/", "/input", "/output").hasRole("USER")
	            .and()
		            .formLogin()
						.loginPage("/login")
						.defaultSuccessUrl("/input", true)
						.permitAll()
				.and()
		            .logout();
	        
	    }	    
	    
	}	

}
