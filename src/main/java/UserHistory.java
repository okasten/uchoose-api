import java.util.HashMap;
import java.util.Map;

public class UserHistory {
    public static Map<User,ViewedRestaurants> userHistory = new HashMap<>();

    UserHistory(){
        // Will create user history map if has not already been created and write to JSON file?
    }

    public static void addUser(User user){
        userHistory.put(user,user.viewed);
    }

    public static String viewAllHistory(){
        String userH = "";
        for (Map.Entry<User,ViewedRestaurants> entry : userHistory.entrySet()){
            userH += "Username" + ":"+entry.getKey().username + ":" + entry.getValue().toString();

        }
        return userH;
    }
}
