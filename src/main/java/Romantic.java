import java.util.*;

public class Romantic extends Mood {

    static Map<String, String> searchValues = new HashMap<String,String>() {{
        put("price","2,3");
        put("term","romantic");
        put("category","Restaurants");
    }};

    public static QueryString getRomantic(String location) {
        QueryString qs = new QueryString("location", location);
            searchValues.forEach(qs::add);

            return qs;
    }


    }

