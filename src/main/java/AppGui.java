import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.swing.*;
import javax.swing.border.LineBorder;

import org.json.JSONException;
import org.json.simple.parser.ParseException;

class AppGui{
    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel controlPanel;
    private JMenuBar menuBar;
    private JMenu menu;
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
        statusLabel.setSize(500,100);
  
        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
  
        mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
        mainFrame.add(statusLabel);
        mainFrame.setVisible(true);  
     }

    private void showOpeningFrame(){
        headerLabel.setText("Uchoose");
        menuBar = new JMenuBar();  
        menu = new JMenu("Menu");
        menuBar.add(menu);

        JButton loginButton = new JButton("Login");
        JButton signupButton = new JButton("Signup");
        
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showLoginFrame();
                controlPanel.remove(loginButton);
                controlPanel.remove(signupButton);
                mainFrame.setJMenuBar(menuBar);
            }
        });

         signupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                showCreateUserFrame();
                controlPanel.remove(loginButton);
                controlPanel.remove(signupButton);
                mainFrame.setJMenuBar(menuBar);
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
                    controlPanel.removeAll();
                    controlPanel.repaint();
                    showFindRestaurantFrame();
                }
                else{
                    statusLabel.setText("Username already exists, please choose a different username.");
                }
            }
        });

        controlPanel.add(create);
        goBackMenuItem();   
    }

    private void goBackMenuItem(){
        JMenuItem goBack = new JMenuItem("Back to Main Page");
        menu.add(goBack);
        goBack.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                controlPanel.removeAll();
                controlPanel.repaint();
                mainFrame.setJMenuBar(null);
                showOpeningFrame();
            }
        });
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

        goBackMenuItem();
    }

    private void showFindRestaurantFrame(){
        headerLabel.setText("I'm Hungry");
        statusLabel.setText("");
        menu.removeAll();
        logoutMenuItem();
        changeLocationMenuItem();
        viewedRestaurantsMenuItem();
        restaurantSearch();
        adventurousSearch();
    }

    private void viewedRestaurantsMenuItem(){
        JMenuItem viewedRes = new JMenuItem("View Saved Restaurants");
        menu.add(viewedRes);

        viewedRes.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                controlPanel.removeAll();
                controlPanel.repaint();
                controlPanel.revalidate();
                viewedRestaurantsFrame();
            }
        });
    }

    private void findRestaurantMenuItem(){
        JMenuItem resSearch = new JMenuItem("Find Food");
        menu.add(resSearch);

        resSearch.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                controlPanel.removeAll();
                controlPanel.repaint();
                controlPanel.revalidate();
                showFindRestaurantFrame();

            }
        });
    }

    private void viewedRestaurantsFrame(){
        findRestaurantMenuItem();
        statusLabel.setText("");
        statusLabel.removeAll();
        headerLabel.setText("<html><b>Saved Restaurants</b></html>");
        try{
            String rstring = UserHistory.viewAllHistory(user);
            String result = "<html>";
            String[] loc = rstring.split("\n");
            for(String r: loc){
                if(r.equals("")) continue;
                String[] rest = r.split("\\[");
                String name = rest[0];
                String[] address = rest[1].split(",", 2);
                String street = address[0].replaceAll("\"", "");
                String city = address[1].replace("\"", "").replace("]", "");
                result += ("<br/><b>" + name + "</b><br/>" + street + "<br/>" + city + "<br/>");
            }
            result += "</html>";
            JLabel restaurants = new JLabel(result);
            JScrollPane scroll = new JScrollPane();
            scroll.createVerticalScrollBar();
            scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scroll.setViewportView(restaurants);
            scroll.setPreferredSize(new Dimension(500, 150));
            controlPanel.add(scroll);
        }
        catch(IOException | JSONException| ParseException e){
            controlPanel.add(new JLabel("Oops. There was a problem fetching saved restaurants"));
        }
        
    }

    private void changeLocationMenuItem(){
        JMenuItem changeLoc = new JMenuItem("Change Location");
        menu.add(changeLoc);

        changeLoc.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                controlPanel.removeAll();
                controlPanel.repaint();
                controlPanel.revalidate();
                changeLocationFrame();
            }
        });
    }

    private void changeLocationFrame(){
        findRestaurantMenuItem();
        headerLabel.setText("Change Location");
        JLabel label = new JLabel("Zipcode");
        JTextField newZip = new JTextField("", 20);
        JButton submit = new JButton("Update Location");
        statusLabel.removeAll();
        statusLabel.setText("Changing location will change current session's location only");
        
        controlPanel.add(label);
        controlPanel.add(newZip);
        controlPanel.add(submit);

        submit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                user.changeLocation(newZip.getText());
                controlPanel.removeAll();
                controlPanel.repaint();
                controlPanel.revalidate();
                statusLabel.setText("");
                showFindRestaurantFrame();
            }
        });
    }

    private void logoutMenuItem(){
        JMenuItem logout = new JMenuItem("Logout");
        menu.add(logout);

        logout.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                controlPanel.removeAll();
                controlPanel.repaint();
                statusLabel.setText("");
                user = null;
                mainFrame.setJMenuBar(null);
                showOpeningFrame();
            }
        });
    }

    private void adventurousSearch(){
        JLabel adventurousLabel = new JLabel("We'll give you some coordinates, and it'll be a fun surprise!", JLabel.CENTER);
        JButton adventurousButton = new JButton("Feeling Adventurous?");
        controlPanel.add(adventurousLabel);
        controlPanel.add(adventurousButton);

        adventurousButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try {
                    callRestaurant(null);
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }
            }
        });
    }
    private void restaurantSearch(){
        JLabel moodLabel = new JLabel("What's your mood?", JLabel.CENTER);
        JComboBox<String> moodsDropdown = new JComboBox<String>(Mood.getMoods());
        JButton searchButton = new JButton("Find me FOOD!");
        
        controlPanel.add(moodLabel);
        controlPanel.add(moodsDropdown);
        controlPanel.add(searchButton);

        searchButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try {
                    callRestaurant(moodsDropdown.getSelectedItem().toString());
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }
            }
        });
    }

    private void formatRestaurant(Restaurant r){
        String[] loc = r.location.split(",");
        String street = loc[0].replace("[\"", "").replace("\"", "");
        String city = loc[1].replace("\"", "") + "," + loc[2].replace("\"]", "");
        statusLabel.setLayout(new FlowLayout());
        statusLabel.setText("<html><b>We chose:</b><em> <br/>" + r.name + "<br/>" + street + "<br/>" + city + "</em></html>");
    }

    private void saveRestaurantButton(Restaurant r){
        statusLabel.removeAll();
        JButton saveButton = new JButton("Save Restaurant");
        statusLabel.add(saveButton);

        saveButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                    User.viewed.addToViewed(r);
                    UserHistory.addUser(user);
                }
                catch(JSONException | ParseException | IOException ex){
                    System.out.println("Save failed");
                }
                
            }
        });
    }

    private void callRestaurant(String mood) throws ParseException {
        try{
            if(mood != null){
                QueryString qs = Mood.getQs(mood, user.location);
                Restaurant r = Restaurant.getResturant(qs);
                formatRestaurant(r);
                saveRestaurantButton(r);
            }
            else{
                String r = Restaurant.feelingAdventurous(user.location);
                statusLabel.setText("<html><b>Good Luck:</b><br/>" + r + "</html>");
            }
        }
        catch (JSONException j){
            statusLabel.setText("Hmmm, something went wrong while choosing your restaurant");
        }
        catch (MalformedURLException j){
            statusLabel.setText("Hmmm, something went wrong while choosing your restaurant");
        }
        catch (IOException j){
            statusLabel.setText("Hmmm, something went wrong while choosing your restaurant");
        }
    }
}
