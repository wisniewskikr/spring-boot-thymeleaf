package pl.kwi.springboot.services;

import java.util.Properties;

import org.springframework.stereotype.Service;

@Service
public class IdService {
	
	
	private static final String ID_PROP = "id";
	private Properties props;

	
	public IdService(){		
		props = System.getProperties();
	}

	
	/**
	 * Method saves name to system properties.
	 * 
	 * @param id object String with id
	 */
	public void save(String id){
		props.setProperty(ID_PROP, id);
	}
	
	/**
	 * Method loads name from system properties.
	 * 
	 * @return object String with id
	 */
	public String load() {
		return props.getProperty(ID_PROP);
	}
	
	
}
