import java.util.ArrayList;

public class ViewedRestaurants{

    ArrayList<Restaurant> viewedRestaurants = new ArrayList<Restaurant>();

 public ArrayList<Restaurant> addToViewed(Restaurant r){
     viewedRestaurants.add(r);
     return viewedRestaurants;
 }

 public String toString(){
     String viewed= "";
     for (Restaurant r:viewedRestaurants){
         viewed +=r;
     }
         return viewed;
 }
}

