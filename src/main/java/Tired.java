import java.util.HashMap;
import java.util.Map;

public class Tired extends Mood{

    Map<String, String> searchValues = new HashMap<String,String>() {{
        put("price","1,2");
        put("term","coffee");
    }};

    public QueryString getTired() {
        QueryString qs = new QueryString("location", location);
        searchValues.forEach((k,v) -> qs.add(k,v));

        return qs;
    }


}