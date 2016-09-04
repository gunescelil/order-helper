package sp2016.cs310.com.orderhelper;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ManagerActivity extends AppCompatActivity {


    ArrayList<String> result = new ArrayList<String>();
    ArrayList<Restaurant> finalRestaurant = new ArrayList<Restaurant>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        final ListView rest = (ListView) findViewById(R.id.showRestListview);


        ContentSelect r = new ContentSelect();
        String[] params= {"get_restaurant.php","manager_id",Manager.getInstance().getManagerID()};
        try {
            result=r.select(params);
            for(Integer i = 0; i<result.size(); i=i+2)
            {
                Restaurant newItem = new Restaurant();

                newItem.setName(result.get(i));
                newItem.setRestaurant_id(result.get(i+1));

                finalRestaurant.add(newItem);
            }


        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ArrayAdapter<Restaurant> AA = new listAdapter();

        rest.setAdapter(AA);

        rest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ManagerActivity.this,ManagersRestaurantPageActivity.class);
                Restaurant r = (Restaurant) rest.getItemAtPosition(position);
                Bundle b = new Bundle();
                b.putString("res_id",r.getRestaurant_id());
                intent.putExtras(b);
                startActivity(intent);
            }
        });


    }


    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Closing Activity")
                .setMessage("Are you sure you want to close this session?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        System.exit(0);
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }

    public class listAdapter extends ArrayAdapter<Restaurant>{
        public listAdapter(){
            super(ManagerActivity.this, R.layout.restaurant, finalRestaurant);
        }

        public View getView(int position, View convertView, ViewGroup parent){
            View itemView = convertView;
            if (itemView == null){
                itemView = getLayoutInflater().inflate(R.layout.restaurant, parent, false);
            }

            Restaurant current = finalRestaurant.get(position);

            TextView resNametext = (TextView) itemView.findViewById(R.id.restaurantname);
            resNametext.setText(current.getName());

            TextView resID = (TextView) itemView.findViewById(R.id.restaurantID);
            resID.setText(current.getRestaurant_id());



            return itemView;
        }
    }
}
