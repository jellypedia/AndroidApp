package com.example.androidprojectcollection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;

public class activity_button_exercise extends AppCompatActivity {

    Button btnClose;
    Button btnToast;
    Button btnChangeBG;
    Button btnButtonBG;
    Button btnDisappear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_exercise);

        btnClose = findViewById(R.id.btnClose);
        btnToast = findViewById(R.id.btnToast);
        btnChangeBG = findViewById(R.id.btnChangeBG);
        btnButtonBG = findViewById(R.id.btnButtonBG);
        btnDisappear = findViewById(R.id.btnDisappear);

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ReturnAct.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

        btnToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = getApplicationContext();
                Toast toastMsg = Toast.makeText(context,"This is a toast message ulol",Toast.LENGTH_SHORT);
                toastMsg.show();
            }
        });

        btnChangeBG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConstraintLayout csl = findViewById(R.id.layout);
                Random rdm = new Random();

                csl.setBackgroundColor(Color.rgb(rdm.nextInt(256),rdm.nextInt(256),rdm.nextInt(256)));
            }
        });

        btnButtonBG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random rdm = new Random();

                btnButtonBG.setBackgroundColor(Color.argb(255,rdm.nextInt(256),rdm.nextInt(256),rdm.nextInt(256)));
            }
        });

        btnDisappear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnDisappear.setVisibility(View.INVISIBLE);
            }
        });
    }
}