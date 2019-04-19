package pl.kwi.springboot.socialmedia.config;

import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.github.connect.GitHubConnectionFactory;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.security.AuthenticationNameUserIdSource;

import pl.kwi.springboot.socialmedia.dao.UsersDao;
import pl.kwi.springboot.socialmedia.services.AccountConnectionSignUpService;

import javax.sql.DataSource;

/**
 * Created by magnus on 18/08/14.
 */
@Configuration
@EnableSocial
public class SocialConfig implements SocialConfigurer {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UsersDao usersDao;

    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer connectionFactoryConfigurer, Environment environment) {
    	
        connectionFactoryConfigurer.addConnectionFactory(new FacebookConnectionFactory(
            environment.getProperty("tmp.facebook.appId"),
            environment.getProperty("tmp.facebook.appSecret")));
        connectionFactoryConfigurer.addConnectionFactory(new GoogleConnectionFactory(
            environment.getProperty("tmp.google.appId"),
            environment.getProperty("tmp.google.appSecret")));
        connectionFactoryConfigurer.addConnectionFactory(new GitHubConnectionFactory(
            environment.getProperty("tmp.github.appId"),
            environment.getProperty("tmp.github.appSecret")));

    }

    @Override
    public UserIdSource getUserIdSource() {
        return new AuthenticationNameUserIdSource();
    }

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource,connectionFactoryLocator, Encryptors.noOpText());
        repository.setConnectionSignUp(new AccountConnectionSignUpService(usersDao));
        return repository;
    }
}
