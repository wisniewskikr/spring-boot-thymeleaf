package pl.kwi.springboot.providers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    
	@Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
		
		handleRememberUsername();
		
        String username = auth.getName();
        String password = auth.getCredentials().toString();
 
        if ("user".equals(username) && "password".equals(password)) {
            return new UsernamePasswordAuthenticationToken(username, password, AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));
        } else {
            throw new BadCredentialsException("External system authentication failed");
        }
        
    }
 
    @Override
    public boolean supports(Class<?> auth) {
        return auth.equals(UsernamePasswordAuthenticationToken.class);
    }
    
    private void handleRememberUsername() {
    	
    	ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = attr.getRequest();
		HttpServletResponse response = attr.getResponse();
		
		String rememberUsername = request.getParameter("rememberUsername");
		Cookie myCookie = new Cookie("username", request.getParameter("username"));		
	    if (!"on".equals(rememberUsername)) {
	    	myCookie.setMaxAge(0);
		}
	    response.addCookie(myCookie);
    	
    }
    
    
}
