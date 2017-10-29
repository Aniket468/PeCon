package com.example.aniketkumar.hawkeye;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

import static java.sql.Types.NULL;

public class NameID extends AppCompatActivity {
    EditText name, ID, pass, dob;
    Button sign;
Context context;
    String check;
    String nam,idd,passs,date;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_id);
        name = (EditText) findViewById(R.id.nam);
        ID = (EditText) findViewById(R.id.login);
        pass = (EditText) findViewById(R.id.pass);
        dob = (EditText) findViewById(R.id.dob);
        sign = (Button) findViewById(R.id.sign);
        context=getApplicationContext();
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText().toString().equals("") && pass.getText().toString().equals("") && dob.getText().toString().equals("") && ID.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "ENTER ALL FIELDS", Toast.LENGTH_SHORT).show();
                }
                     else {
                    nam=name.getText().toString();
                    idd=ID.getText().toString();
                    passs=pass.getText().toString();
                    date=dob.getText().toString();
                    BackgroundTask1 back=new BackgroundTask1();
                    back.execute(idd);

                }
            }
        });
    }

    public class BackgroundTask1 extends AsyncTask<String, Void, Void> {

        String res;
        String ID;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(String... params) {
            String user_url = "http://192.168.43.187:1234/hawk/sign.php";

            String id = params[0];


            //  Log.d("TAGG::",id);
            try {
                URL url = new URL(user_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                String data = URLEncoder.encode("login","UTF-8") + "=" + URLEncoder.encode(id, "UTF-8");
                Log.d("TAGG","data=" + data);
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                InputStream is = new BufferedInputStream(httpURLConnection.getInputStream());
                res = convertStreamToString(is);
                Log.d("TAGG::","res=" + res);


            } catch (MalformedURLException e) {
                Log.d("TAGG::", "error1=" + e.toString());
            } catch (IOException e) {
                Log.d("TAGG::", "error2=" + e.toString());
            }
//        if (res != null) {
//            try {
//                JSONObject jsonObj = new JSONObject(res);
//                // Getting JSON Array node
//                JSONArray contacts = jsonObj.getJSONArray("result");
//
//                // looping through All Contacts
//                for (int i = 0; i < contacts.length(); i++) {
//                    JSONObject c = contacts.getJSONObject(i);
//                    String latitude = c.getString("LATITUDE");
//                    String longitude = c.getString("LONGITUDE");
//                    Log.e("LATITUDE ", latitude);
//                    Log.d("LONGITUDE",longitude);
//
//
//
//
//
//                    // tmp hash map for single contact
//
//
//                    // adding each child node to HashMap key => value
//
//
//
//                    // adding contact to contact list
//
//                }
//                Log.e("TAG:::::",contact.toString());
//            } catch (final JSONException e) {
//                Log.e("TAG", "Json parsing error: " + e.getMessage());
//
//                    }
//
//
//            }
//
//         else {
//            Log.e("TAG", "Couldn't get json from server.");
//
//        }


            return null;
        }


        private String convertStreamToString(InputStream is) {

            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();

            String line = null;
            try {
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return sb.toString();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Log.e("RESUL",res);

            //int p=Integer.parseInt(res);
            Log.e("RESUL:","1");

            if(res.equals("1"))
            {
                Log.e("RESULT", "in if ");
                Toast.makeText(getApplicationContext(),"registering.... !",Toast.LENGTH_SHORT).show();
                BackgroundTask backgroundTask=new BackgroundTask();
                backgroundTask.execute(nam,idd,passs,date);
            }
            else

            {
                Log.e("RESULT", "in if ");
                Toast.makeText(getApplicationContext(),"registering.... !",Toast.LENGTH_SHORT).show();
                BackgroundTask backgroundTask=new BackgroundTask();
                backgroundTask.execute(nam,idd,passs,date);

            }

        }


    }
    public class BackgroundTask extends AsyncTask<String,Void,Void> {



        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(String... params) {
            String user_url="http://192.168.43.187:1234/hawk/signing.php";

            String name=params[0];

                String id  = params[1];
                String pass= params[2];
            String dob=params[3];
                Log.d("TAGG::","name="+name+"id="+id);
                try {
                    URL url =new URL(user_url);
                    HttpURLConnection httpURLConnection =(HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    OutputStream os=httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                    String data= URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"+
                            URLEncoder.encode("login","UTF-8")+"="+ URLEncoder.encode(id,"UTF-8")+"&"+
                    URLEncoder.encode("pass","UTF-8")+"="+URLEncoder.encode(pass,"UTF-8")+"&"+
                            URLEncoder.encode("dob","UTF-8")+"="+URLEncoder.encode(dob,"UTF-8");
                    Log.d("TAGG","data="+data);
                    bufferedWriter.write(data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    InputStream is=new BufferedInputStream(httpURLConnection.getInputStream());

                    String res=convertStreamToString(is);
                    Log.d("TAGG::","res="+res);

                    check=res;
                } catch (MalformedURLException e) {
                    Log.d("TAGG::","error1="+ e.toString());
                } catch (IOException e) {
                    Log.d("TAGG::","error2="+ e.toString());
                }




            return null;
        }

        private String convertStreamToString(InputStream is) {

            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();

            String line = null;
            try {
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return sb.toString();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Log.d("TAGG::",check);
            String p="FAILED";
            Toast.makeText(getApplicationContext(),"You signing up "+check,Toast.LENGTH_SHORT).show();
            SharedPreferences sharedPreferences= getSharedPreferences("com.example.aniketkumar.suspect_app", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putString("ID",ID.getText().toString());
            editor.commit();

        }


    }

}

