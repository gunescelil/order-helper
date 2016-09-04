package sp2016.cs310.com.orderhelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.ExecutionException;

public class ManagersRestaurantPageActivity extends AppCompatActivity {


    ArrayList<String> month_result = new ArrayList<String>();
    ArrayList<String> week_result = new ArrayList<String>();
    ArrayList<String> day_result = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managers_restaurant);
        Button manageWaiter = (Button) findViewById(R.id.button_manage_waiters);
        Button manageMeals = (Button) findViewById(R.id.button_manage_meals);
        Button finacialPosition = (Button) findViewById(R.id.button_financial_position);
        Intent i =getIntent();
        Bundle extras = i.getExtras();
        final String ResID=extras.getString("res_id");

        manageWaiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManagersRestaurantPageActivity.this,ManageWaitersActivity.class);
                startActivity(intent);
            }
        });

        manageMeals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManagersRestaurantPageActivity.this,ManageMealsActivity.class);
                startActivity(intent);
            }
        });

        finacialPosition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView today = (TextView) findViewById(R.id.todays_Revenue);
                TextView week = (TextView) findViewById(R.id.weeklyRevenue);
                TextView month = (TextView) findViewById(R.id.monthly_Revenue);

                Calendar m = Calendar.getInstance();
                m.add(Calendar.MONTH,-1);
                SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                String formattedDate = df.format(m.getTime());



                String[] arguments=new String[]{"financial.php","restaurant_id", ResID,"Date_String", formattedDate};

                ContentSelect r = new ContentSelect();

                try {
                    month_result=r.select(arguments);
                    String s = new String() ;

                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                month.setText("$"+month_result.get(0));

                Calendar w = Calendar.getInstance();
                w.add(Calendar.DAY_OF_MONTH,-7);
                SimpleDateFormat wf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                String formattedDateW = wf.format(w.getTime());



                String[] arg=new String[]{"financial.php","restaurant_id", ResID,"Date_String", formattedDateW};

                ContentSelect k = new ContentSelect();

                try {
                    week_result=k.select(arg);
                    String s = new String() ;

                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                week.setText("$"+week_result.get(0));

                Calendar d = Calendar.getInstance();
                d.add(Calendar.DAY_OF_MONTH,-1);
                SimpleDateFormat dfd = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                String formattedDateD = dfd.format(d.getTime());



                String[] args=new String[]{"financial.php","restaurant_id", ResID,"Date_String", formattedDateD};

                ContentSelect l = new ContentSelect();

                try {
                    day_result=l.select(arg);
                    String s = new String() ;

                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                today.setText("$"+week_result.get(0));





            }
        });





    }
}
