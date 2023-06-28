package org.appDesktop.service;

public class UserService {

    public static String getUserId(){
        PropertiesReader propertiesReader = new PropertiesReader("user.properties");
        return propertiesReader.getProperty().getProperty("user.id");
    };

    public static void setUserId(String userId){
        PropertieWriter propertieWriter = new PropertieWriter();
        propertieWriter.writeNewPropertie("user.properties", "user.id", userId);
    }
}
