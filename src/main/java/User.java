import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.*;
import org.apache.commons.io;

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

    }

    public User(){    
        Scanner scn = new Scanner(System.in);
        System.out.println("Enter Username");
        this.username = scn.nextLine();
        System.out.println("Enter First Name");
        this.firstName = scn.nextLine();
        System.out.println("Enter Last Name");
        this.lastName = scn.nextLine();
        System.out.println("Enter Zipcode");
        this.location = scn.nextLine();
    }

    public void changeLocation( String location){
        this.location = location;
    }
    public boolean userExists(){
        File file = new File("data/Users.txt");
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if(line.contains(this.username)) { 
                    System.out.println("User already exists.");
                    return true;
                }
            }
        } 
        catch(FileNotFoundException e) {
        }
        File file = new File("data/Users.txt");
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if(line.contains(this.username)) { 
                    System.out.println("User already exists.");
                    return true;
                }
            }
        } catch(FileNotFoundException e) {
        }
        return false; 
    }

    public void createUserAccount(){
        
        try{
            FileWriter myWriter = new FileWriter("data/Users.txt", true);
            BufferedWriter bw = new BufferedWriter(myWriter);
            PrintWriter out = new PrintWriter(bw);
            String userInfo = this.username + "," + this.firstName + "," + this.lastName + "," + this.location;
            out.println(userInfo);
            out.close();
        }

        catch (IOException e){}
    }


    public static void main(String[] args){
        File file = new File("data/Users.txt");
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if(line.contains("vee143")) { 
                    System.out.println("User already exists.");
                }
            }
        } catch(FileNotFoundException e) {
        }
        
        
        

    }

    


    

}