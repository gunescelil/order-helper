package sp2016.cs310.com.orderhelper;

import android.app.ActionBar;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class TablesActivity extends AppCompatActivity {

    private static ArrayList<String> tableResult= new ArrayList<String>();
    private static ArrayList<Table> tables = new ArrayList<Table>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tables);
        final LinearLayout lleft = (LinearLayout) findViewById(R.id.linearLeft);
        final LinearLayout lright = (LinearLayout) findViewById(R.id.linearRight);


            String[] params ={"get_tables.php","res_id",Waiter.getInstance().getWaiterRestaurantId()};
        ContentSelect r = new ContentSelect();
        try {
            tableResult = r.select(params);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        initializeTableArray(tableResult);
        String s = new String();

        LinearLayout.LayoutParams parameters = new LinearLayout.LayoutParams(
                ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);

        for(int i=0; i<tables.size();i++)
        {

            LinearLayout ll = new LinearLayout(this);
            ll.setOrientation(LinearLayout.HORIZONTAL);

            final Button btn = new Button(this);
            final Integer buttonID = Integer.parseInt(tables.get(i).getTable_id());
            btn.setId(buttonID+1);
            btn.setText(tables.get(i).getTable_id());
            btn.setLayoutParams(parameters);
            btn.setTextSize(25);
            if(tables.get(i).getFull().equals("1"))
            {
                btn.setTextColor(Color.RED);
            }
            else if(!tables.get(i).getFull().equals("0"))
            {
                btn.setTextColor(Color.WHITE);
            }

            final Integer index = i;

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(getApplicationContext(), "Opening Table "+buttonID, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(TablesActivity.this,SeeTableActivity.class);
                    Bundle b = new Bundle();
                    String s = buttonID.toString();

                    b.putString("table_id",s);

                    intent.putExtras(b);
                    startActivity(intent);

                }
            });

            ll.addView(btn);
            if(i % 2 == 0){
                lleft.addView(ll);
            }
            else{
                lright.addView(ll);
            }




        }





    }

    @Override
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


    public void initializeTableArray(ArrayList<String> alist){
        for(int i=0; i<alist.size();i=i+3)
        {
            Table t = new Table();
            t.setTable(alist.get(i),alist.get(i+1),alist.get(i+2));
            tables.add(t);
        }
    }



}
