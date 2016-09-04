package sp2016.cs310.com.orderhelper;

import java.util.ArrayList;

/**
 * Created by asus on 26.05.2016.
 */
public class Order {
    private String item_name;
    private ArrayList<Order> orders;

    public String getItem_count() {
        return item_count;
    }

    public void setItem_count(String item_count) {
        this.item_count = item_count;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getWaiter_id() {
        return waiter_id;
    }

    public void setWaiter_id(String waiter_id) {
        this.waiter_id = waiter_id;
    }

    public String getTable_id() {
        return table_id;
    }

    public void setTable_id(String table_id) {
        this.table_id = table_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private String item_count;
    private String waiter_id;
    private String table_id;
    private String date;
    private String price;

    Order(){}

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setOrder(String n,String i, String p)
    {
        item_name=n;
        item_count=i;
        price=p;
    }

    public void addItem(Order order)
    {
        orders.add(order);
    }




}
