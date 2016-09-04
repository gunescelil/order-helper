package sp2016.cs310.com.orderhelper;

/**
 * Created by asus on 24.05.2016.
 */
public class Waiter {

    private static Waiter ourInstance = new Waiter();

    public static Waiter getInstance() {
        return ourInstance;
    }

    private Waiter() {
    }
    private String id;
    private String name;
    private String username;
    private String restaurant_id;


    public String getWaiterRestaurantId(){return restaurant_id;}
    public String getWaiterID(){
        return id;
    }
    public String getWaiterName(){
        return name;
    }
    public String getWaiterUserName(){
        return username;
    }
    public void setWaiter(String i, String n, String u, String r){
        id=i;
        name=n;
        username=u;
        restaurant_id=r;
    }
}
