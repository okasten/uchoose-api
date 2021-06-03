import java.util.ArrayList;

public class Mood  {
    User user = new User();
    static Romantic romantic = new Romantic();
    QueryString qs;

    public  QueryString getRestaurant(String mood){
        // uses getRomantic method from specific mood class
        if(mood == "Romantic"){
           qs = romantic.getRomantic();
        }
        return qs;
    }

    public static void main(String[] args) {

        System.out.println(Mood.romantic.getRestaurant("Romantic"));
    }
}



