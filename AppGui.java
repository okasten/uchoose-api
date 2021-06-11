import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class AppGui{
    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel controlPanel;
    private JLabel msglabel;
    private User user;

    public AppGui(){
        prepareGUI();
    }

    public static void main(String[] args){
        AppGui appgui = new AppGui();  
        appgui.showOpeningFrame();
    }

    private void prepareGUI(){
        mainFrame = new JFrame("Uchoose Start");
        mainFrame.setSize(600,600);
        mainFrame.setLayout(new GridLayout(3, 1));
        
        mainFrame.addWindowListener(new WindowAdapter() {
           public void windowClosing(WindowEvent windowEvent){
              System.exit(0);
           }        
        });    
        headerLabel = new JLabel("Welcome to Uchoose!", JLabel.CENTER);        
        statusLabel = new JLabel("",JLabel.CENTER);    
        statusLabel.setSize(350,100);
        msglabel = new JLabel("", JLabel.CENTER);
  
        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
  
        mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
        mainFrame.add(statusLabel);
        mainFrame.setVisible(true);  
     }

    private void showOpeningFrame(){
        headerLabel.setText("Uchoose");   

        JButton loginButton = new JButton("Login");
        JButton signupButton = new JButton("Signup");
        
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               showLoginFrame();
               controlPanel.remove(loginButton);
               controlPanel.remove(signupButton);
            }
        });

         signupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                showCreateUserFrame();
                controlPanel.remove(loginButton);
                controlPanel.remove(signupButton);
            }
        });
        controlPanel.add(loginButton);
        controlPanel.add(signupButton);
        mainFrame.setVisible(true);  
    }

    private void showCreateUserFrame(){
        headerLabel.setText("Create Account");
        JTextField username = new JTextField("Username", 20);
        JTextField firstName = new JTextField("First Name", 20);
        JTextField lastName = new JTextField("Last Name", 20);
        JTextField zipcode = new JTextField("Zip Code", 20);

        controlPanel.add(username);
        controlPanel.add(firstName);
        controlPanel.add(lastName);
        controlPanel.add(zipcode);

        JButton create = new JButton("Create Account");
        create.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                boolean validated = User.createUserAccount(username.getText(), firstName.getText(), lastName.getText(), zipcode.getText());
                if(validated){
                    user = new User(username.getText(), firstName.getText(), lastName.getText(), zipcode.getText(), false);
                    controlPanel.remove(username);
                    controlPanel.remove(firstName);
                    controlPanel.remove(lastName);
                    controlPanel.remove(zipcode);
                    controlPanel.remove(create);
                }
                else{
                    statusLabel.setText("Username already exists, please choose a different username.");
                }
            }
        });

        controlPanel.add(create);
    }

    private void showLoginFrame(){
        headerLabel.setText("Existing User Login");
        JTextField username = new JTextField("Please enter your username", 20);
        controlPanel.add(username);

        JButton login = new JButton("Login");
        controlPanel.add(login);
        login.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                user = User.userExists(username.getText());
                if(user != null){
                    controlPanel.remove(username);
                    controlPanel.remove(login);
                    showFindRestaurantFrame();
                }
                else{
                    statusLabel.setText("User does not exist, please create an account.");
                }
                
            }
        });
    }

    private void showFindRestaurantFrame(){
        headerLabel.setText("I'm Hungry");
        JLabel moodLabel = new JLabel("What's your mood?");
        JComboBox<String> moodsDropdown = new JComboBox<String>(Mood.getMoods());
        JButton searchButton = new JButton("Find me FOOD!");
        
        controlPanel.add(moodLabel);
        controlPanel.add(moodsDropdown);
        controlPanel.add(searchButton);

        searchButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                QueryString qs = Mood.getQs("tired", user.location);
                System.out.println(qs);
            }
        });
    }

}
