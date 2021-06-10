import java.util.HashMap;
import java.util.Map;

public class Healthy extends Mood{

    static Map<String, String> searchValues = new HashMap<String,String>() {{
        put("price","1,2,3");
        put("term","healthy");
        put("category","Restaurants");
    }};

    public static QueryString getHealthy(String location) {
        QueryString qs = new QueryString("location", location);
        searchValues.forEach((k,v) -> qs.add(k,v));

        return qs;
    }


}