import java.util.ArrayList;

public class Mood  {
    String location = Restaurant.user.location;
    static Romantic romantic = new Romantic();
    static Sad sad = new Sad();
    static Healthy healthy = new Healthy();
    static Tired tired = new Tired();
    static Lazy lazy = new Lazy();
    static Idk idk = new Idk();
    public static String[] moods = {"Romantic","Sad","Healthy","Tired","Lazy","Idk"};
    QueryString qs;

    public  QueryString getQs(String mood){

        if(mood.equalsIgnoreCase(moods[0])){
           qs = romantic.getRomantic();
        }
        if(mood.equalsIgnoreCase(moods[1])) {
            qs = sad.getSad();
        }
        if(mood.equalsIgnoreCase(moods[2])) {
            qs = healthy.getHealthy();
        }
        if(mood.equalsIgnoreCase(moods[3])){
            qs = tired.getTired();
        }
        if(mood.equalsIgnoreCase(moods[4])){
            qs = lazy.getLazy();
        }
        if(mood.equalsIgnoreCase(moods[5])){
            qs = idk.getIdk();
        }
        return qs;
    }

    public static String[] getMoods(){
       return moods;
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



