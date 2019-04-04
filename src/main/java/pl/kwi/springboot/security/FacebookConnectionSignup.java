package pl.kwi.springboot.security;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Service;

@Service
public class FacebookConnectionSignup implements ConnectionSignUp {

    @Override
    public String execute(Connection<?> connection) {
        System.out.println("signup === ");
        return connection.getDisplayName();
    }

}
