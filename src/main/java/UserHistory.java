import com.fasterxml.jackson.databind.JsonMappingException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UserHistory {
    public static Map<String,ViewedRestaurants> userHistory = new HashMap<>();

    UserHistory(){
        // Will create user history map if has not already been created and write to JSON file?
    }

    public static void addUser(User user) throws JSONException, IOException, ParseException {
        userHistory.put(user.username,user.viewed);
       JSONParser parser = new JSONParser();
       Object obj = parser.parse(new FileReader("data/ViewRestaurant.json"));
       JSONObject objJsonObject = new JSONObject(obj.toString());
       String oldValue = objJsonObject.getString(user.username);
        //JSONObject list = objJsonObject.getJSONObject(user.username);
        String userHis = oldValue+ userHistory.get(user.username).toString();
        objJsonObject.put(user.username, (userHis));
        try {
            FileWriter myWriter = new FileWriter("data/ViewRestaurant.json");
            myWriter.write(objJsonObject.toString());
            myWriter.close();
        }
        catch(IOException e){}
    }

    public static String viewAllHistory(User user) throws IOException, JSONException, ParseException {
      //Read from file, return value for given username
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("data/ViewRestaurant.json"));
        JSONObject objJsonObject = new JSONObject(obj.toString());
        String oldValue = objJsonObject.getString(user.username);
        return (oldValue);

    }

    public static void main(String[] args) throws JSONException, FileNotFoundException, IOException, ParseException, JsonMappingException {





    }

}
