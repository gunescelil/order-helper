package sp2016.cs310.com.orderhelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class SeeTableActivity extends AppCompatActivity {

    ArrayList<String> result = new ArrayList<String>();
    ArrayList<Order> final_result = new ArrayList<Order>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_table);

        Bundle extras = getIntent().getExtras();
        String table_id=extras.getString("table_id");


        final ListView orderedLV = (ListView) findViewById(R.id.listviewOrderedMeals);

        Button add = (Button) findViewById(R.id.button_add_item);
        Button clear = (Button) findViewById(R.id.button_delete_items);
        Button pay = (Button) findViewById(R.id.button_go_to_payment);

        String[] params= {"get_ordered_items.php","table_id",table_id};
        ContentSelect r = new ContentSelect();
        try {
            result=r.select(params);
            for(Integer i = 0; i<result.size(); i=i+3)
            {
                Order o = new Order();
                o.setItem_name(result.get(i));
                o.setItem_count(result.get(i+1));
                o.setPrice(result.get(i+2));


                final_result.add(o);
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ArrayAdapter<Order> AA = new listAdapter();
        orderedLV.setAdapter(AA);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SeeTableActivity.this,ShowCategoriesActivity.class);
                startActivity(intent);

            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO:

            }
        });

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SeeTableActivity.this,PaymentActivity.class);
                startActivity(intent);
            }
        });



    }

    public class listAdapter extends ArrayAdapter<Order> {
        public listAdapter(){
            super(SeeTableActivity.this, R.layout.ordereditem, final_result);
        }

        public View getView(int position, View convertView, ViewGroup parent){
            View itemView = convertView;
            if (itemView == null){
                itemView = getLayoutInflater().inflate(R.layout.ordereditem, parent, false);
            }

            Order current = final_result.get(position);

            TextView orNametext = (TextView) itemView.findViewById(R.id.tv_ItemName);
            orNametext.setText(current.getItem_name());

            TextView orCount = (TextView) itemView.findViewById(R.id.tv_ItemCount);
            orCount.setText(current.getItem_count());

            TextView price = (TextView) itemView.findViewById(R.id.tv_Price);
            price.setText(current.getPrice());

            TextView total = (TextView) itemView.findViewById(R.id.tv_total_price);
            Integer i = Integer.parseInt(current.getPrice());
            Integer j = Integer.parseInt(current.getItem_count());
            total.setText(i*j+"");


            return itemView;
        }
    }
}
