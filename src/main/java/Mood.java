
public class Mood  {
    //String location = Restaurant.user.location;
    public static String[] moods = {"Romantic","Sad","Healthy","Tired","Lazy","Idk"};
    static  QueryString qs;

    public  static QueryString getQs(String mood, String location) throws IllegalArgumentException {

        if(mood.equalsIgnoreCase(moods[0])){
           qs = Romantic.getRomantic(location);
        }
        else if (mood.equalsIgnoreCase(moods[1])) {
            qs = Sad.getSad(location);
        }
        else if (mood.equalsIgnoreCase(moods[2])) {
            qs = Healthy.getHealthy(location);
        }
        else if (mood.equalsIgnoreCase(moods[3])){
            qs = Tired.getTired(location);
        }
        else if(mood.equalsIgnoreCase(moods[4])){
            qs = Lazy.getLazy(location);
        }
        else if(mood.equalsIgnoreCase(moods[5])){
            qs = Idk.getIdk(location);
        }
        else
            throw new IllegalArgumentException("Enter a valid Mood");
        return qs;
    }

    public static String[] getMoods(){
       return moods;
    }

//    public static void main(String[] args) {
//        User test = new User("Test","Test","Test","60622");
//        System.out.println(Mood.getQs("Romantic",test.location));
//        System.out.println(Mood.sad.getQs("Sad",test.location));
//        System.out.println(Mood.healthy.getQs("HeALtHY",test.location));
//        System.out.println(Mood.tired.getQs("TIrEd",test.location));
//        System.out.println(Mood.lazy.getQs("lAzy",test.location));
//        System.out.println(Mood.idk.getQs("idk",test.location));
//    }
}



