import java.util.*;
public class Sad extends Mood{
    Map<String, String> searchValues = new HashMap<>() {{
        put("price","1");
        put("term","dessert");
    }};

    public QueryString getSad() {
        QueryString qs = new QueryString("location", user.location);
        searchValues.forEach((k,v) -> qs.add(k,v));

        return qs;
    }
}
