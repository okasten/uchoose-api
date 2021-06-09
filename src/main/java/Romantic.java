import java.util.*;

public class Romantic extends Mood {

    Map<String, String> searchValues = new HashMap<String,String>() {{
        put("price","2,3");
        put("term","romantic");
        put("category","Restaurants");
    }};

    public QueryString getRomantic() {
        QueryString qs = new QueryString("location", location);
            searchValues.forEach((k,v) -> qs.add(k,v));

            return qs;
    }


    }

