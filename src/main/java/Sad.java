import java.util.*;
public class Sad extends Mood{
    static Map<String, String> searchValues = new HashMap<>() {{
        put("price","1");
        put("term","dessert");
    }};

    public static QueryString getSad(String location) {
        QueryString qs = new QueryString("location", location);
        searchValues.forEach(qs::add);

        return qs;
    }
}
