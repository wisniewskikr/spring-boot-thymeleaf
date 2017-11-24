package pl.kwi.springboot.services;

import java.util.Properties;

import org.springframework.stereotype.Service;

@Service
public class NameService {
	
	
	private static final String NAME_PROP = "name";
	private Properties props;

	
	public NameService(){		
		props = System.getProperties();
	}

	
	/**
	 * Method saves name to system properties.
	 * 
	 * @param name object String with name
	 */
	public void save(String name){
		props.setProperty(NAME_PROP, name);
	}
	
	/**
	 * Method loads name from system properties.
	 * 
	 * @return object String with name
	 */
	public String load() {
		return props.getProperty(NAME_PROP);
	}
	
	
}
