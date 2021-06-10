import java.util.HashMap;
import java.util.Map;

public class Lazy extends Mood {

    static Map<String, String> searchValues = new HashMap<String,String>() {{
        put("price","1");
        put("category","Fast Food");
    }};

    public static QueryString getLazy(String location) {
        QueryString qs = new QueryString("location", location);
        searchValues.forEach(qs::add);

        return qs;
    }


}