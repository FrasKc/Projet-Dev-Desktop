package org.appDesktop.service;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;


@Data
public class PropertiesReader {
    private String propertieName;
    private Properties properties;

    public PropertiesReader(String propertieName){
        this.propertieName = propertieName;
        this.properties = new Properties();
    }
    public Properties getProperty(){
        java.net.URL url = ClassLoader.getSystemResource(propertieName);
        try  {
            properties.load(url.openStream());
        } catch (IOException fie) {
            fie.printStackTrace();
        }
        return properties;
    }
}




