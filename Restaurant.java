import java.util.*;
import java.io.*;
import java.net.*;

public class Restaurant{
    public String id;
    public String name;
    public String location;


public Restaurant(String id, String name, String location){
    this.id = id;
    this.name = name;
    this.location = location;

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


public static void getResturant(QueryString query) throws MalformedURLException, IOException{
    URL url = new URL(query.toString());
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    con.setRequestProperty("Authorization", getKey());
    con.setRequestMethod("GET");
    int responseCode = con.getResponseCode();
    System.out.println("GET Response Code :: " + responseCode);
    if (responseCode == HttpURLConnection.HTTP_OK) { // success
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        System.out.println(response.toString());
		} 
        else {
			System.out.println("GET request not worked");
		}

	}


    public static void main(String[] args) throws MalformedURLException,IOException {
        QueryString query = new QueryString("location","01503");
        getResturant(query);

        Mood moodSearch = new Mood();
        QueryString qs = moodSearch.getQs("tired");
        getResturant(qs);
    }



}


