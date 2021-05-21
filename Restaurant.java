import java.util.*;
import java.io.*;

public class Restaurant{
    public String id;
    public String name;
    public String location;

public Restaurant(String id, String name, String location){
    this.id = id;
    this.name = name;
    this.location = location;

}

private String getKey(){
    Properties prop = new Properties();
    String fileName = "app.config";
    InputStream is = null;
    try {
        is = new FileInputStream(fileName);
    } 
    catch (FileNotFoundException ex) {}
    
    try {
        prop.load(is);
    } 
    catch (IOException ex) {}
    
    return prop.getProperty("api_key");
    }   
}