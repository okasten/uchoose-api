import java.util.ArrayList;

public class Mood {
    String price;
    static Romantic romantic = new Romantic();


    public  Restaurant getRestaurant(String mood){
        Restaurant pick = null;
        if(mood == "Romantic"){
           pick = romantic.getRomantic();

        }
        return pick;
    }

    public static void main(String[] args) {


        Mood.romantic.setRestaurant();
        Restaurant pick = romantic.getRestaurant("Romantic");

        System.out.println(pick.name);
    }
}



