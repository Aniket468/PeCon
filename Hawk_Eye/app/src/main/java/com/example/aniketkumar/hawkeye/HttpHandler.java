package com.example.aniketkumar.hawkeye;

/**
 * Created by Aniket Kumar on 11-Oct-17.
 */
import android.util.Log;

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
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

public class HttpHandler {

    private static final String TAG = HttpHandler.class.getSimpleName();

    public HttpHandler() {
    }

    public String makeServiceCall(String reqUrl,String parid) {
        String response = null;
        try {
//            URL url = new URL(reqUrl);
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("POST");
//            conn.setDoOutput(true);
//            OutputStream os=conn.getOutputStream();
//            BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
//            String data= URLEncoder.encode("PARID","UTF-8")+"="+ URLEncoder.encode(parid,"UTF-8");
//            Log.d("TAGG","data="+data);
//            bufferedWriter.write(data);
//            bufferedWriter.flush();
//            bufferedWriter.close();
//
//            InputStream in = new BufferedInputStream(conn.getInputStream());
//            response = convertStreamToString(in);
            URL url =new URL(reqUrl);
            Log.d("TAGG","data="+"1");
            HttpURLConnection httpURLConnection =(HttpURLConnection) url.openConnection();
            Log.d("TAGG","data="+"2");
            httpURLConnection.setRequestMethod("POST");
            Log.d("TAGG","data="+"3");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            Log.d("TAGG","data="+"4");
            OutputStream os=httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
            Log.d("TAGG","data="+"5");
            String data= URLEncoder.encode("PARID","UTF-8")+"="+ URLEncoder.encode(parid,"UTF-8");
            Log.d("TAGG","data="+data);
            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            InputStream is=new BufferedInputStream(httpURLConnection.getInputStream());
            response=convertStreamToString(is);
            Log.d("results","res="+response);
        } catch (MalformedURLException e) {
            Log.e("TAGG", "MalformedURLException: " + e.getMessage());
        } catch (ProtocolException e) {
            Log.e("TAGG", "ProtocolException: " + e.getMessage());
        } catch (IOException e) {
            Log.e("TAGG", "IOException: " + e.getMessage());
        } catch (Exception e) {
            Log.e("TAGG", "Exception: " + e.getMessage());
        }
        return response;
    }

    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
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

}
