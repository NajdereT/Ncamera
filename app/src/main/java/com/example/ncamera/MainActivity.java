package com.example.ncamera;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.PointF;
import android.nfc.Tag;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.dd.morphingbutton.MorphingButton;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.util.ArrayList;

import info.hoang8f.widget.FButton;

public class MainActivity extends AppCompatActivity {

    private FButton fButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Default theme should be set before content view is added

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



    }



    public void onClick (View view){
        View include = (View)findViewById(R.id.include);
        include.setVisibility(View.VISIBLE);


    }
    public void exit (View view){
        View include = (View)findViewById(R.id.include);
        include.setVisibility(View.GONE);


    }

    public void came (View view){
        Intent intent = new Intent(this, CamActivity.class);
        startActivity(intent);
    }

    public void permission(View view){
        checkCameraPermission();
    }
    public static final String TAG = "MainActivity";

    private final int MY_PERMISSIONS_REQUEST_USE_CAMERA = 0x00AF;
    private void checkCameraPermission(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA ) != PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG,"Permission not available requesting permission");
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_USE_CAMERA);
        } else {
            Log.d(TAG,"Permission has already granted");
            Toast.makeText(MainActivity.this,
                    "Permission has already granted", Toast.LENGTH_SHORT).show();


        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_USE_CAMERA: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG,"permission was granted! Do your stuff");
                } else {
                    Log.d(TAG,"permission denied! Disable the function related with permission.");
                }
                return;
            }
        }
    }


}
