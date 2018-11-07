package com.example.ncamera;

import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.google.ar.core.ArCoreApk;
import com.sackcentury.shinebuttonlib.ShineButton;

import info.hoang8f.widget.FButton;
import smartdevelop.ir.eram.showcaseviewlib.GuideView;

public class CamActivity extends AppCompatActivity {

    private GuideView mGuideView;
    private GuideView.Builder builder;
    private ShineButton shineButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cam);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        shineButton = (ShineButton) findViewById(R.id.po_image2);
        shineButton.init(CamActivity.this);

        FloatingActionButton floatingActionButton = (FloatingActionButton)findViewById(R.id.floatingActionButton);

        new GuideView.Builder(this)
                .setTitle("Info!")
                .setContentText("voor snelle informatie van wat alles doet klik hier")
                .setGravity(GuideView.Gravity.auto) //optional
                .setDismissType(GuideView.DismissType.anywhere) //optional - default GuideView.DismissType.targetView
                .setTargetView(floatingActionButton)
                .setContentTextSize(12)//optional
                .setTitleTextSize(14)//optional
                .build()
                .show();

    }
    public void ar (View view){
        maybeEnableArButton();


    }

    public void info (View view){
       final FButton fButton1 = findViewById(R.id.flb4);
        final FButton fButton2 = findViewById(R.id.flb5);
       final FButton fButton3 = findViewById(R.id.flb6);
       final CardView cardView = findViewById(R.id.cardview);



        builder = new GuideView.Builder(CamActivity.this)
                .setTitle("hirt komt de ")
                .setContentText("Guide Description Text\n .....Guide Description Text\n .....Guide Description Text .....")
                .setGravity(GuideView.Gravity.center)
                .setDismissType(GuideView.DismissType.outside)
                .setTargetView(cardView)
                .setGuideListener(view1 -> {
                    switch (view1.getId()){
                        case  R.id.flb4:
                            builder.setTargetView(fButton1).build();
                            break;
                        case R.id.flb5:
                            builder.setTargetView(fButton2).build();
                            break;
                        case R.id.flb6:
                            builder.setTargetView(fButton3).build();
                            break;

                        case R.id.cardview:
                            return;
                    }
                    mGuideView = builder.build();
                    mGuideView.show();
                });

        mGuideView = builder.build();
        mGuideView.show();



    }




    public void scan (View view){
        Toast.makeText(CamActivity.this,
                "start scanner", Toast.LENGTH_SHORT).show();


    }

    public void cam (View view) {
        Toast.makeText(CamActivity.this,
                "start camera", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, Camera_activity.class);
        startActivity(intent);
    }




    void maybeEnableArButton() {
        ArCoreApk.Availability availability = ArCoreApk.getInstance().checkAvailability(this);
        if (availability.isTransient()) {
            // Re-query at 5Hz while compatibility is checked in the background.
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    maybeEnableArButton();
                }
            }, 200);
        }
        if (availability.isSupported()) {
            Toast.makeText(CamActivity.this,
                    "hoera je telefoon kan AR", Toast.LENGTH_LONG).show();
            // indicator on the button.
        } else { // Unsupported or unknown.
            Toast.makeText(CamActivity.this,
                    "helaas het werknt niet op jouw telefoon", Toast.LENGTH_LONG).show();
        }
    }
}
