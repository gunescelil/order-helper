package sp2016.cs310.com.orderhelper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ShowCategoriesActivity extends AppCompatActivity {


    ArrayList<String> result = new ArrayList<String>();
    ArrayList<Order> final_result = new ArrayList<Order>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_categories);

        final ListView orderedLV = (ListView) findViewById(R.id.showCategories_lv);
        String[] params= {"get_categories.php",};

    }
}
