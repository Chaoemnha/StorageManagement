package storage.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
	private Properties properties = null;
	private static ConfigLoader configLoader = null;
		String profileName = "config.properties";
	private ConfigLoader() {
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(profileName);
		if(inputStream!=null) {
			properties = new Properties();
			try {
				properties.load(inputStream);
			} catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	};
	
	public static ConfigLoader getInstance() {
		//Xly don luong
		if(configLoader==null) {
			synchronized (ConfigLoader.class) {
				configLoader = new ConfigLoader();
			}
		}
		return configLoader;
	}
	
	public String getValue(String key) {
		if(properties.containsKey(key)) {
			return properties.getProperty(key);
		}
		return null;
	}
	
}
