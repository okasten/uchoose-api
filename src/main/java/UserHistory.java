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
    public static Map<User,ViewedRestaurants> userHistory = new HashMap<>();

    UserHistory(){
        // Will create user history map if has not already been created and write to JSON file?
    }

    public static void addUser(User user) throws JSONException, IOException, ParseException {
        userHistory.put(user,user.viewed);
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("data/ViewRestaurant.json"));
        JSONObject objJsonObject = new JSONObject(obj.toString());
        objJsonObject.put(user.username, (userHistory.get(user.username)).toString());
        try {
            FileWriter myWriter = new FileWriter("data/ViewRestaurant.json");
            myWriter.write(objJsonObject.toString());
            myWriter.close();
        }
        catch(IOException e){}
    }

    public static String viewAllHistory(){
        String userH = "";
        for (Map.Entry<User,ViewedRestaurants> entry : userHistory.entrySet()){
            userH += "Username" + ":"+entry.getKey().username  + "{" + entry.getValue().toString() +"}" ;

        }

        return userH;
    }

    public static void main(String[] args) throws JSONException, FileNotFoundException, IOException, ParseException, JsonMappingException {





    }

}
