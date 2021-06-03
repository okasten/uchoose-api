import java.util.HashMap;
import java.util.Map;

public class Idk extends Mood{

    Map<String, String> searchValues = new HashMap<String,String>() {{
        put("price","1,2,3");
        put("category","Restaurants");
        put("attributes", "hot_and_new");
    }};

    public QueryString getIdk() {
        QueryString qs = new QueryString("location", user.location);
        searchValues.forEach((k,v) -> qs.add(k,v));

        return qs;
    }


}