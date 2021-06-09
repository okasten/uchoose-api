import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class UChooseTests {

    @Test
    public void NoRepeatRestaurantsBackToBackTest() throws JSONException, IOException {
        InputStream sysInBackup = System.in; // backup System.in to restore it later
        ByteArrayInputStream in = new ByteArrayInputStream(("jdoe"+ System.lineSeparator()+ "John"+System.lineSeparator()+"Doe"+System.lineSeparator()+"01503").getBytes());
        System.setIn(in);
        String expectedLocation = "01503";
        QueryString query1 = new QueryString("location",expectedLocation);
        QueryString query2 = new QueryString("location",expectedLocation);
        Restaurant first = (Restaurant.getResturant(query1));
        Restaurant second = (Restaurant.getResturant(query1));
        System.setIn(sysInBackup);
        Assert.assertNotSame("Each run should return different restaurant",first,second);

    }

    @Test //RestaurantClassTest
    public void restaurantToStringTest() throws JSONException, IOException {
        InputStream sysInBackup = System.in; // backup System.in to restore it later
        ByteArrayInputStream in = new ByteArrayInputStream(("jdoe"+ System.lineSeparator()+ "John"+System.lineSeparator()+"Doe"+System.lineSeparator()+"01503").getBytes());
        System.setIn(in);
        String expectedLocation = "01503";
        QueryString query = new QueryString("location",expectedLocation);
        Restaurant r = (Restaurant.getResturant(query));
        String actual = r.toString();
        String expected = r.name + "\n" +r.location;
        Assert.assertEquals(actual,expected);
    }

//    @Test
//    public void addToViewedRestaurantsTest(){
//        InputStream sysInBackup = System.in; // backup System.in to restore it later
//        ByteArrayInputStream in = new ByteArrayInputStream(("jdoe"+ System.lineSeparator()+ "John"+System.lineSeparator()+"Doe"+System.lineSeparator()+"01503").getBytes());
//        System.setIn(in);
//        String expectedLocation = "01503";
//        QueryString query = new QueryString("location",expectedLocation);
//        Restaurant r = (Restaurant.getResturant(query));
//
//    }

}
