import java.util.*;
import java.io.*;
import java.net.*;
import org.json.*;


public class Restaurant{
    public String id;
    public String name;
    public String location;
    public String longitude;
    public String latitude;
    public static User user = new User();

public Restaurant() {}


public Restaurant(String id, String name, String location, String longitude, String latitude){
    this.id = id;
    this.name = name; 
    this.location = location;
    this.longitude = longitude;
    this.latitude = latitude;
}



private static String getKey(){
    Properties prop = new Properties();
    String fileName = "src/app.config";
    InputStream is = null;
    try {
        is = new FileInputStream(fileName);
    } 
    catch (FileNotFoundException ex) {}
    
    try {
        prop.load(is);
    } 
    catch (IOException ex) {}
    
    return "Bearer "+ prop.getProperty("api_key");
    }   

public static Restaurant getResturant(QueryString query) throws MalformedURLException, IOException, JSONException{
    Restaurant result = new Restaurant();
    URL url = new URL(query.toString());
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    con.setRequestProperty("Authorization", getKey());
    con.setRequestMethod("GET");
    int responseCode = con.getResponseCode();
    if (responseCode == HttpURLConnection.HTTP_OK) { // success
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        result = processResults(response.toString());
       
		} 
        else {
			System.out.println("GET request not worked");
		}
        return result;

	}

    public static Restaurant processResults(String response) throws JSONException {
        Random rand = new Random();
        int upperbound = 20;
        int pick = rand.nextInt(upperbound);
        JSONObject object = new JSONObject(response);
        JSONArray getArray = object.getJSONArray("businesses");
        JSONObject resturant = getArray.getJSONObject(pick);
        String address = (resturant.getJSONObject("location").getJSONArray("display_address")).toString();
        String longitude = (String.valueOf(resturant.getJSONObject("coordinates").getDouble("longitude")));
        String latitude = (String.valueOf(resturant.getJSONObject("coordinates").getDouble("latitude")));
        Restaurant result = new Restaurant(resturant.getString("id"),resturant.getString("name"),address,longitude,latitude);
        return result;

    }

    public static String feelingAdventurous() throws MalformedURLException,IOException, JSONException{
    Restaurant mysteryRestaurant;
        QueryString query = new QueryString("location",user.location);
        mysteryRestaurant = (getResturant(query));
        return ( mysteryRestaurant.latitude + "," + mysteryRestaurant.longitude);

    }
    @Override
    public String toString(){
        return this.name + "\n" + this.location;
    }



    public static void main(String[] args) throws MalformedURLException,IOException, JSONException {
        QueryString query = new QueryString("location","01503");
        System.out.println(getResturant(query));
//
//        Mood moodSearch = new Mood();
//        QueryString qs = moodSearch.getQs("lazy");
//        System.out.println(getResturant(qs));
//
//        System.out.println(feelingAdventurous());
   }



}


