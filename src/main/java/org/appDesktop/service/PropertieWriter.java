package org.appDesktop.service;

import lombok.Data;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

@Data
public class PropertieWriter {
    private Properties propertie;

    public PropertieWriter() {
        this.propertie = new Properties();
    }

    public void writeNewPropertie(String fileName, String propertieKey, String propertieValue){
        FileReader reader = null;
        FileWriter writer = null;
        try {
            String file = String.format("src/main/resources/%s",fileName);
            reader = new FileReader(file);
            writer = new FileWriter(file);

            this.propertie.load(reader);

            this.propertie.setProperty(propertieKey,propertieValue);
            this.propertie.store(writer,"write a file");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
