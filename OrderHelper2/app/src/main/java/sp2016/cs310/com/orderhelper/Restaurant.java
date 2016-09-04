package sp2016.cs310.com.orderhelper;

/**
 * Created by asus on 26.05.2016.
 */
public class Restaurant {

    public String getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(String restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String restaurant_id;
    private String  name;

    Restaurant(){}

    public void setRestaurant(String i, String n){
        restaurant_id=i;
        name=n;
    }




}
