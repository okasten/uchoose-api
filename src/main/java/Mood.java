import java.util.ArrayList;

public class Mood  {
    String location = Restaurant.user.location;
    static Romantic romantic = new Romantic();
    static Sad sad = new Sad();
    static Healthy healthy = new Healthy();
    static Tired tired = new Tired();
    static Lazy lazy = new Lazy();
    static Idk idk = new Idk();
    QueryString qs;

    public  QueryString getQs(String mood){

        if(mood.equalsIgnoreCase("romantic")){
           qs = romantic.getRomantic();
        }
        if(mood.equalsIgnoreCase("sad")) {
            qs = sad.getSad();
        }
        if(mood.equalsIgnoreCase("healthy")) {
            qs = healthy.getHealthy();
        }
        if(mood.equalsIgnoreCase("tired")){
            qs = tired.getTired();
        }
        if(mood.equalsIgnoreCase("lazy")){
            qs = lazy.getLazy();
        }
        if(mood.equalsIgnoreCase("idk")){
            qs = idk.getIdk();
        }
        return qs;
    }

    public static void main(String[] args) {

        System.out.println(Mood.romantic.getQs("Romantic"));
        System.out.println(Mood.sad.getQs("Sad"));
        System.out.println(Mood.healthy.getQs("HeALtHY"));
        System.out.println(Mood.tired.getQs("TIrEd"));
        System.out.println(Mood.lazy.getQs("lAzy"));
        System.out.println(Mood.idk.getQs("idk"));
    }
}



