import java.util.HashMap;
import java.util.Map;

public class Lazy extends Mood {

    Map<String, String> searchValues = new HashMap<String,String>() {{
        put("price","1");
        put("category","Fast Food");
    }};

    public QueryString getLazy() {
        QueryString qs = new QueryString("location", user.location);
        searchValues.forEach((k,v) -> qs.add(k,v));

        return qs;
    }


}