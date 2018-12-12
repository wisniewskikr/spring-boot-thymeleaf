package pl.kwi.springboot.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
    protected void configure(HttpSecurity http) throws Exception {

        http
        	.authorizeRequests()        		
        		.antMatchers("/css/**", "/js/**", "/images/**").permitAll()
        		.anyRequest().authenticated()
            .and()
	            .formLogin()
					.loginPage("/login")
					.defaultSuccessUrl("/input", true)
					.permitAll()
			.and()
	            .logout()
	        .and().rememberMe()
	        	.key("rem-me-key");
        
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("user").password("{noop}password").roles("USER");
        
    }
    
}
