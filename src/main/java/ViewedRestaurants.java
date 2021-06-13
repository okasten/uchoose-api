import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.JsonMappingException;
import org.json.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ViewedRestaurants{

    ArrayList<Restaurant> viewedRestaurants = new ArrayList<Restaurant>();

 public ArrayList<Restaurant> addToViewed(Restaurant r){
     viewedRestaurants.add(r);
     return viewedRestaurants;
 }
@Override
 public String toString(){
     String viewed= "";
     for (Restaurant r:viewedRestaurants){
         viewed +=r;
     }
         return viewed;
 }

    public static void main(String[] args) throws JSONException, FileNotFoundException, IOException, ParseException, JsonMappingException {
     User test = new User("awatkins","Andrea","Watkins","60622");
     QueryString query1 = new QueryString("location", test.location);
     Restaurant.getResturant(query1);

     //for reading the JSON file and returning the list of viewed restaurants
     JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("data/ViewRestaurant.json"));
        JSONObject objJsonObject = new JSONObject(obj.toString());
        JSONArray list = objJsonObject.getJSONArray("vee123");
        //System.out.println(list);

        //for writing and updating the file
        ObjectMapper mapper = new ObjectMapper();
        String key = "vee123";
        JSONObject root = mapper.readValue(new File("data/ViewRestaurant.json"), JSONObject.class);
        String val_older = root.getString(key);
        System.out.println(val_older);




    }

}

