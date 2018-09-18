package pl.kwi.springboot.providers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import pl.kwi.springboot.services.LoginAttemptService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	private LoginAttemptService loginAttemptService;
    
	@Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
		
        String username = auth.getName();
        String password = auth.getCredentials().toString();
        
        if (loginAttemptService.isBlocked(username)) {
        	throw new RuntimeException("blocked");
        }
 
        if ("user".equals(username) && "password".equals(password)) {
        	loginAttemptService.loginSucceeded(username);
            return new UsernamePasswordAuthenticationToken(username, password, AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));
        } else {
        	loginAttemptService.loginFailed(username);
            throw new BadCredentialsException("External system authentication failed");
        }
        
    }
 
    @Override
    public boolean supports(Class<?> auth) {
        return auth.equals(UsernamePasswordAuthenticationToken.class);
    }
    
}
