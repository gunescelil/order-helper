package sp2016.cs310.com.orderhelper;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by asus on 24.05.2016.
 */
public class ContentSelect {

    ArrayList<String> result = new ArrayList<String>();


    ContentSelect() {
    }

    public ArrayList<String> select(String[] params) throws ExecutionException, InterruptedException {


        answerTask a = new answerTask();
        result= a.execute(params).get();

        return result;
    }

    public void setResult(ArrayList<String> s)
    {
        result =s;
    }


    class  answerTask extends AsyncTask<String, Void, ArrayList<String>>
    {



        answerTask()
        {

        }
        @Override
        protected ArrayList<String> doInBackground(String... params) {



            try {

                URL url = new URL("http://10.0.2.2/order_helper/" + params[0]);
                //URL url = new URL("http://10.50.100.248/order_helper/" + params[0]);
                //URL url = new URL("http://192.168.1.20/order_helper/" + params[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setDoOutput(true);
                urlConnection.setDoInput(true);
                urlConnection.setRequestMethod("POST");
                urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                urlConnection.connect();


                StringBuilder input = new StringBuilder();
                if(params.length > 1) {
                    input.append(params[1] + "=" + params[2]);

                    for (int i = 3; i <= params.length - 2; i = i + 2) {
                        input.append("&" + params[i] + "=" + params[i + 1]);
                    }
                }


                OutputStreamWriter wr = new OutputStreamWriter(urlConnection.getOutputStream());
                wr.write(input.toString());
                wr.flush();
                wr.close();

                BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                String line;
                urlConnection.disconnect();

                while ((line = reader.readLine()) != null) {
                    result.add(line);
                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;

        }



        @Override
        protected void onPostExecute(ArrayList<String> s) {
            setResult(s);

            super.onPostExecute(s);

        }


    }


    class  questionTask extends AsyncTask<String, Void, String>
    {
        @Override
        protected String doInBackground(String... params) {


            return null;
        }
    }




}
