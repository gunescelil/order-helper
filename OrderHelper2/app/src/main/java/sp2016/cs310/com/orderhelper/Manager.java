package sp2016.cs310.com.orderhelper;

/**
 * Created by asus on 24.05.2016.
 */
public class Manager {
    private static Manager ourInstance = new Manager();

    public static Manager getInstance() {
        return ourInstance;
    }

    private Manager() {
    }
    private String id;
    private String name;
    private String username;


    public String getManagerID(){
        return id;
    }
    public String getManagerName(){
        return name;
    }
    public String getManagerUserName(){
        return username;
    }
    public void setManager(String i, String n, String u){
        id=i;
        name=n;
        username=u;
    }

}
