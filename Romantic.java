import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Romantic extends Mood {

    ArrayList<Restaurant> romanticRestaurants = new ArrayList<Restaurant>();


    int numRestaurants = romanticRestaurants.size();

    public void setRestaurant() {
        //WIll be set using Yelp API
        Restaurant OliveGarden = new Restaurant("2", "OliveGarden", "MKE");
        Restaurant McDonalds = new Restaurant("3", "McDonalds", "MKE");
        Restaurant TacoBell = new Restaurant("4", "TacoBell", "MKE");
        romanticRestaurants.add(OliveGarden);
        romanticRestaurants.add(McDonalds);
        romanticRestaurants.add(TacoBell);
    }


    public Restaurant getRomantic() {
        //Chooses random restaurant from romantic restaurant arraylist
        Random random_index = new Random();
        int index = random_index.nextInt(romanticRestaurants.size());
        return (romanticRestaurants.get(index));
    }

}