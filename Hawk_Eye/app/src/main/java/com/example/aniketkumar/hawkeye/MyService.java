package com.example.aniketkumar.hawkeye;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import java.net.Socket;

public class MyService extends Service {
    private static int ACCESS_FINE_LOCATION_CONSTANT;
    Thread m_objThreadclient;
    //EditText ediTtext ,ip;
    Socket clientSocket;
    // Button connect;
    String ipserver;
    public MyService() {
    }
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Bundle extras = intent.getExtras();
        //String message= (String) extras.get("KEY1");
        //ipserver=message;
        LocationManager locationManager = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
        // Define a listener that responds to location updates
        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                // Called when a new location is found by the network location provider.
                String s1 = location.toString();
                Double lati = location.getLatitude();
                Double longi = location.getLongitude();

                Log.d("TAGGL::", "lati=" + lati + "longi=" + longi);
                SharedPreferences sharedPreferences = getSharedPreferences("com.example.aniketkumar.suspect_app", Context.MODE_PRIVATE);
                String message = sharedPreferences.getString("ID", "Default");
                BackgroundLocation backgroundLocation = new BackgroundLocation();
                backgroundLocation.execute(message, "" + lati, "" + longi);
                //sendLocation(""+lati,""+longi);

            }

            public void onStatusChanged(String provider, int status, Bundle extras) {

            }


            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };


// Register the listener with the Location Manager to receive location updates
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {


        /*  //  AlertDialog.Builder builder = new AlertDialog.Builder(Myservice.this);
            // builder.setTitle("Need Location permission");
            builder.setMessage("This app needs location permission.");
            builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                   // ActivityCompat.requestPermissions(Myservice.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, ACCESS_FINE_LOCATION_CONSTANT);
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.show();*/
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
        return START_STICKY;
    }


}
