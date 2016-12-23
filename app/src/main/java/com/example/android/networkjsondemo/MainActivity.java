package com.example.android.networkjsondemo;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String MY_KEY_SERVICE = "My Key";
    private static final String TAG = "MainActivityTAG_";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void checkConnection(View view) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if(activeNetworkInfo != null && activeNetworkInfo.isConnected()){
            Toast.makeText(this, "Device is connected to the network.", Toast.LENGTH_LONG).show();
            Log.d(TAG, "activeNetworkInfo: " + activeNetworkInfo);
            Log.d(TAG, "activeNetworkInfo.isConnected(): " +activeNetworkInfo.isConnected());
        }
        else{
            Toast.makeText(this, "Device has not internet access.", Toast.LENGTH_SHORT).show();
        }
    }

    public void getData(View view) {
        Intent intent = new Intent(this, MyIntentService.class);
        intent.putExtra(MY_KEY_SERVICE, "hahahaha");
        startService(intent);
    }
}
