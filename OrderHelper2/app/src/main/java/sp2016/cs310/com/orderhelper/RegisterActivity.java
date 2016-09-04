package sp2016.cs310.com.orderhelper;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class RegisterActivity extends AppCompatActivity {

    private static ArrayList<String> result=null;
    @InjectView(R.id.et_Password_RegisterActivity) EditText _passwordText;
    ArrayList<String> tableResult=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.inject(this);

        final EditText et_Name = (EditText) findViewById(R.id.et_name_RegisterActivity);
        final EditText et_UserName = (EditText) findViewById(R.id.et_Username_RegisterActivity);
        final EditText password = (EditText) findViewById(R.id.et_Password_RegisterActivity);
        Button signup_button= (Button) findViewById(R.id.sing_up_button_register_activity);


        ContentSelect r = new ContentSelect();
        String[] params ={"get_tables.php","res_id","1"};
        try {
            tableResult = r.select(params);

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = et_Name.getText().toString();
                String username = et_UserName.getText().toString();
                String pass = password.getText().toString();

                String[] params = {"register.php","name",name,"username",username,"password",pass};

                ContentSelect r = new ContentSelect();

                if(validate())
                {
                    try {
                        result=r.select(params);
                        if(result.get(0).equals("Success")   )
                        {
                            android.app.AlertDialog.Builder builder =new android.app.AlertDialog.Builder(RegisterActivity.this);
                            builder.setMessage("Congratulations!You have been registered.").create().show();

                            Intent i = new Intent(RegisterActivity.this,MainActivity.class);
                            startActivity(i);

                        }
                        else if(result.get(0).equals("Error"))
                        {
                            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(RegisterActivity.this);
                            builder.setMessage("Register Failed. Try another username").create().show();
                        }
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }




            }
        });


    }



    public boolean validate() {
        boolean valid = true;

        String password = _passwordText.getText().toString();
        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }





}
