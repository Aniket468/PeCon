package com.example.aniketkumar.hawkeye;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.wifi.WifiManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {
    private static int ACCESS_FINE_LOCATION_CONSTANT;
    String message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String[] message = new String[1];
        SharedPreferences sharedPreferences= getSharedPreferences("com.example.aniketkumar.suspect_app", Context.MODE_PRIVATE);
        message[0] =sharedPreferences.getString("ID","Default");
        Log.d("TAGG::","message="+ message[0]);
        final TextView textView=(TextView)findViewById(R.id.tv);

        if(message[0].equals("Default"))
        {
            Intent intent=new Intent(getApplicationContext(),NameID.class);
            startActivity(intent);
        }
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        // Define a listener that responds to location updates
        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                String s1 = location.toString();
                Double lati=location.getLatitude();
                Double longi=location.getLongitude();
                textView.setText(""+lati+","+longi);
                SharedPreferences sharedPreferences= getSharedPreferences("com.example.aniketkumar.suspect_app", Context.MODE_PRIVATE);
                message[0] =sharedPreferences.getString("ID","Default");
                Log.d("TAGG::","message="+ message[0]);
                // ediTtext.setText(s1);
                BackgroundLocation backgroundLocation=new BackgroundLocation();
                backgroundLocation.execute(message[0],""+lati,""+longi);
//                sendLocation(""+lati,""+longi);
            }
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }


            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };


// Register the listener with the Location Manager to receive location updates
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {


            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Need Location permission");
            builder.setMessage("This app needs location permission.");
            builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, ACCESS_FINE_LOCATION_CONSTANT);
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.show();
        }
       /* else if (permissionStatus.getBoolean(Manifest.permission.ACCESS_FINE_LOCATION,false)) {
            //Previously Permission Request was cancelled with 'Dont Ask Again',
            // Redirect to Settings after showing Information about why you need the permission
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Need location Permission");
            builder.setMessage("This app needs location permission.");
            builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    sentToSettings = true;
                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri = Uri.fromParts("package", getPackageName(), null);
                    intent.setData(uri);
                    startActivityForResult(intent, REQUEST_PERMISSION_SETTING);
                    Toast.makeText(getBaseContext(), "Go to Permissions to Grant location", Toast.LENGTH_LONG).show();
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.show();
        }*/
        else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 0, locationListener);
        }


        Intent i= new Intent(getApplicationContext(),MyService.class);
        startService(i);



//
//     //   Button developer=(Button)findViewById(R.id.developer);
//        developer.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//                Intent i1=new Intent(getApplicationContext(),MainActivityDeveloper.class);
//                startActivity(i1);
//            }
//        });
        Button track=(Button)findViewById(R.id.track);
        track.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i3=new Intent(getApplicationContext(),MainActivityTrack.class);
                startActivity(i3);

            }
        });
    }
}
