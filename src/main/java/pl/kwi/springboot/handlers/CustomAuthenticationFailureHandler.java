package pl.kwi.springboot.handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		
		if (exception.getClass().isAssignableFrom(BadCredentialsException.class)) {
			response.sendRedirect("/login?errorCode=BAD_CREDENTIALS");
		} else if (exception.getClass().isAssignableFrom(LockedException.class)) {
			response.sendRedirect("/login?errorCode=LOCKED");
		} else {
			response.sendRedirect("/login");
		}
		
	}

}
