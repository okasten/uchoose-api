import java.util.Scanner;

public class User{
    public String username;
    public String firstName;
    public String lastName;
    public String location;

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

}