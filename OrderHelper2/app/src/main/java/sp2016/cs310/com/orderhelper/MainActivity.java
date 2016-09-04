package sp2016.cs310.com.orderhelper;

import android.app.Service;
import android.content.ContentProvider;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    String usertype=null;
    private static ArrayList<String> result=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Setting up the spinner
        final Spinner user_type_spinner = (Spinner)findViewById(R.id.user_type_spinner);

        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.user_array,
                android.R.layout.simple_spinner_item);
        user_type_spinner.setAdapter(arrayAdapter);




        final EditText username = (EditText) findViewById(R.id.et_Username_LoginPage);
        final EditText password = (EditText) findViewById(R.id.et_Password_LoginPage);



        Button login_button = (Button) findViewById(R.id.login_button_LoginPage);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String u_name = username.getText().toString();
                String pass = password.getText().toString();

                ContentSelect r = new ContentSelect();
                usertype= user_type_spinner.getSelectedItem().toString();
                if(usertype.equals("Waiter"))
                {
                    String[] params = {"login.php","username",u_name,"password",pass,"usertype",usertype};
                    try {
                        result = r.select(params);
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if(result.size()==4) {
                        Toast.makeText(getBaseContext(), "Login succeeded", Toast.LENGTH_SHORT).show();
                        Waiter waiter = Waiter.getInstance();
                        waiter.setWaiter(result.get(0),result.get(1),result.get(2),result.get(3));
                        Intent intent = new Intent(MainActivity.this,TablesActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP );
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_SHORT).show();
                    }



                }else if (usertype.equals("Manager"))
                {
                    String[] params = {"login.php","username",u_name,"password",pass,"usertype",usertype};
                    try {
                        result = r.select(params);
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if(result.size()==3) {
                        Manager manager = Manager.getInstance();
                        manager.setManager(result.get(0),result.get(1),result.get(2));
                        Intent intent = new Intent(MainActivity.this,ManagerActivity.class);
                        Toast.makeText(getBaseContext(), "Login succeeded", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });

        TextView registerLink = (TextView) findViewById(R.id.tv_register_link);
        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });




    }







}




