package pl.kwi.springboot.configs;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.naming.Context;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
	
	@Value(value = "${ldap.url}")
    private String ldapUrl;
	
	@Value(value = "${ldap.user}")
    private String ldapUser;
	
	@Value(value = "${ldap.password}")
    private String ldapPassword;
	
	@Bean
	public LdapContext ldapContext() {
		
		LdapContext ldapContext = null;
		
		try {
			
			Map<String, String> map = new HashMap<String, String>();	        
			map.put(Context.SECURITY_AUTHENTICATION, "simple");
			map.put(Context.PROVIDER_URL, ldapUrl);
			map.put(Context.SECURITY_PRINCIPAL, ldapUser);
			map.put(Context.SECURITY_CREDENTIALS, ldapPassword);
			map.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
	        
	        ldapContext = new InitialLdapContext(
	                new Hashtable<String, String>(map), null);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ldapContext;
		
	}

}
