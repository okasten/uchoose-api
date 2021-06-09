import java.util.ArrayList;

public class ViewedRestaurants{

    ArrayList<Restaurant> viewedRestaurants = new ArrayList<Restaurant>();

 public ArrayList<Restaurant> addToViewed(Restaurant r){
     viewedRestaurants.add(r);
     return viewedRestaurants;
 }
}

