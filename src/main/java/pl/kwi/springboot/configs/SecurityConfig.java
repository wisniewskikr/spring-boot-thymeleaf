package pl.kwi.springboot.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.mem.InMemoryUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInController;

import pl.kwi.springboot.security.FacebookConnectionSignup;
import pl.kwi.springboot.security.FacebookSignInAdapter;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = { "pl.kwi.springboot" })
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private ConnectionFactoryLocator connectionFactoryLocator;

    @Autowired
    private UsersConnectionRepository usersConnectionRepository;

    @Autowired
    private FacebookConnectionSignup facebookConnectionSignup;


    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        // @formatter:off
        http
        .csrf().disable()
        .authorizeRequests()
        .antMatchers("/login*","/signin/**","/signup/**").permitAll()
        .anyRequest().authenticated()
        .and()
        .formLogin().loginPage("/login").permitAll()
        .and()
        .logout();
    } // @formatter:on

    @Bean
    public ProviderSignInController providerSignInController() {
        ((InMemoryUsersConnectionRepository) usersConnectionRepository).setConnectionSignUp(facebookConnectionSignup);
        return new ProviderSignInController(connectionFactoryLocator, usersConnectionRepository, new FacebookSignInAdapter());
    }
}