import org.json.JSONException;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import static org.junit.Assert.fail;

public class UChooseTests {

    @Test
    public void NoRepeatRestaurantsBackToBackTest() throws JSONException, IOException, ParseException {
        User test = new User("Test", "Test", "Test", "60622");
        QueryString query1 = new QueryString("location", test.location);
        QueryString query2 = new QueryString("location", test.location);
        Restaurant first = (Restaurant.getResturant(query1));
        Restaurant second = (Restaurant.getResturant(query2));
        Assert.assertNotSame("Each run should return different restaurant", first, second);

    }

    @Test
    public void restaurantToStringTest() throws JSONException, IOException, ParseException {
        User test = new User("Test", "Test", "Test", "60622");
        QueryString query = new QueryString("location", test.location);
        Restaurant r = (Restaurant.getResturant(query));
        String actual = r.toString();
        String expected = r.name + "\n" + r.location;
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void feelingAdventurousTestNoRepeats() throws JSONException, IOException, ParseException {
        User test = new User("Test", "Test", "Test", "60622");
        QueryString query1 = new QueryString("location", test.location);
        QueryString query2 = new QueryString("location", test.location);
        String r1 = Restaurant.feelingAdventurous(test.location);
        String r2 = Restaurant.feelingAdventurous(test.location);
        Assert.assertNotSame(r1, r2);
    }

    @Test
    public void getRestaurantNotWorkedTest() throws JSONException, IOException, ParseException {
        User test = new User("Test", "Test", "Test", "60622");
        QueryString query1 = new QueryString("locaion", test.location);
        Restaurant r = new Restaurant();
        String r1 = r.toString();
        Assert.assertEquals("Null restaurant should be returned & GET request not worked message", r1, Restaurant.getResturant(query1).toString());

    }

    @Test
    public void changeLocation() {
        User user = new User("awatkins", "Andrea", "Watkins", "60622");
        user.changeLocation("02132");
        Assert.assertEquals("02132", user.location);
    }

    @Test
    public void idkMoodTestNoDuplicates() {
        Mood mood = new Mood();
        User test = new User("Test", "Test", "Test", "60622");
        QueryString qs = mood.getQs("idk", test.location);
        QueryString qs1 = mood.getQs("idk", test.location);
        Assert.assertNotSame(qs, qs1);

    }

    @Test
    public void healthyMoodTestNoDuplicates() {
        Mood mood = new Mood();
        User test = new User("Test", "Test", "Test", "60622");
        QueryString qs = mood.getQs("healthy", test.location);
        QueryString qs1 = mood.getQs("healthy", test.location);
        Assert.assertNotSame(qs, qs1);

    }
    @Test
    public void tiredMoodTestNoDuplicates() {
        Mood mood = new Mood();
        User test = new User("Test", "Test", "Test", "60622");
        QueryString qs = mood.getQs("tired", test.location);
        QueryString qs1 = mood.getQs("tired", test.location);
        Assert.assertNotSame(qs, qs1);

    }
    @Test
    public void lazyMoodTestNoDuplicates() {
        Mood mood = new Mood();
        User test = new User("Test", "Test", "Test", "60622");
        QueryString qs = mood.getQs("lazy", test.location);
        QueryString qs1 = mood.getQs("lazy", test.location);
        Assert.assertNotSame(qs, qs1);

    }
    @Test
    public void romanticMoodTestNoDuplicates() {
        Mood mood = new Mood();
        User test = new User("Test", "Test", "Test", "60622");
        QueryString qs = mood.getQs("romantic", test.location);
        QueryString qs1 = mood.getQs("romantic", test.location);
        Assert.assertNotSame(qs, qs1);

    }
    @Test
    public void sadMoodTestNoDuplicates() {
        Mood mood = new Mood();
        User test = new User("Test", "Test", "Test", "60622");
        QueryString qs = mood.getQs("sad", test.location);
        QueryString qs1 = mood.getQs("sad", test.location);
        Assert.assertNotSame(qs, qs1);

    }
    @Test
    public void invalidMoodTest(){
        Mood mood = new Mood();
        User test = new User("Test", "Test", "Test", "60622");
       try {
           QueryString qs = mood.getQs("lit", test.location);
           fail("exception should have occurred");
       } catch (IllegalArgumentException e) {
           Assert.assertEquals(e.getMessage(),"Enter a valid Mood");
       }

    }
    @Test
    public void getMoodsTest(){
        Mood mood = new Mood();
        String[] moods = mood.getMoods();
        String[] expectedMoods = {"Romantic","Sad","Healthy","Tired","Lazy","Idk"};
        Assert.assertArrayEquals(moods,expectedMoods);

    }

    @Test
    public void createUser(){
        Random rand = new Random();
        int upperbound = 1000;
        int num = rand.nextInt(upperbound);
        User test = new User("Test"+ num, "Test", "Test", "60622");
        User actual = User.userExists("Test"+num);
        Assert.assertEquals("Test"+num, actual.username);

    }
    }

