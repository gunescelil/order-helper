package sp2016.cs310.com.orderhelper;

/**
 * Created by asus on 25.05.2016.
 */
public class Table {
    private String table_id;
    private String  personCount;
    private String isFull;


    public String getTable_id() {
        return table_id;
    }

    public void setTable_id(String table_id) {
        this.table_id = table_id;
    }

    public String getPersonCount() {
        return personCount;
    }

    public void setPersonCount(String personCount) {
        this.personCount = personCount;
    }

    public String getFull() {
        return isFull;
    }

    public void setFull(String full) {
        isFull = full;
    }

    Table(){}

    public void setTable(String t, String p, String i){
        table_id=t;
        personCount=p;
        isFull=i;
    }

    Table(String t, String p, String i)
    {
        table_id=t;
        personCount=p;
        isFull=i;
    }
}
