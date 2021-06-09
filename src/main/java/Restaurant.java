import java.util.*;
import java.io.*;
import java.net.*;
import org.json.*;


public class Restaurant{
    public String id;
    public String name;
    public String location;
    public int longitude;
    public int latitude;

public Restaurant() {}

public Restaurant(String id, String name, String location){
    this.id = id;
    this.name = name;
    this.location = location;

}

public Restaurant(String id, String name, String location, int longitude, int latitude){
    this.id = id;
    this.name = name; 
    this.location = location;
    this.longitude = longitude;
    this.latitude = latitude;
}



private static String getKey(){
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
        Restaurant result = new Restaurant(resturant.getString("id"),resturant.getString("name"),address);
        return result;

    }
    @Override
    public String toString(){
    String restaurant = this.name + "\n" + this.location;
    return restaurant;
    }


    public static void main(String[] args) throws MalformedURLException,IOException, JSONException {
        QueryString query = new QueryString("location","01503");
        System.out.println(getResturant(query));

        Mood moodSearch = new Mood();
        QueryString qs = moodSearch.getQs("lazy");
        System.out.println(getResturant(qs));
    }



}


