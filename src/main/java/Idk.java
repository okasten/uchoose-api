import java.util.HashMap;
import java.util.Map;

public class Idk extends Mood{

    static Map<String, String> searchValues = new HashMap<String,String>() {{
        put("price","1,2,3");
        put("category","Restaurants");
        put("sort_by","rating");
    }};

    public static QueryString getIdk(String location) {
        QueryString qs = new QueryString("location", location);
        searchValues.forEach(qs::add);

        return qs;
    }


}