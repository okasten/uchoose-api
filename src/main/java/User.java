import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.*;


public class User{
    public String username;
    public String firstName;
    public String lastName;
    public String location;
    public ViewedRestaurants viewedRestaurants;

    public User(String username, String firstName, String lastName, String location){
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.location = location; 
        createUserAccount(username, firstName, lastName, location);
    }

    public User(){    
    }

    public void changeLocation( String location){
        this.location = location;
    }
    public static boolean userExists(String username){
        boolean results = false;
        File file = new File("data/Users.txt");
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if(line.contains(username)) { 
                    System.out.println("User already exists.");
                    results = true;
                }
            }
        } 
        catch(FileNotFoundException e) {
        }
        return results;
    }

    public static boolean createUserAccount(String username, String firstName, String lastName, String location){
        if (!userExists(username)){
            try{
                FileWriter myWriter = new FileWriter("data/Users.txt", true);
                BufferedWriter bw = new BufferedWriter(myWriter);
                PrintWriter out = new PrintWriter(bw);
                String userInfo = username + "," + firstName + "," + lastName + "," + location;
                out.println(userInfo);
                out.close();
                return true;
            }
            catch (IOException e){
                return false;
            }
            
        }
        else{
            System.out.println("User already exists.");
            return false;
        }
        
    }




    }

