package com.example.practica9;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button beerBtn;
    Button subBtn;
    Button yogurtBtn;
    Button dogBtn;

    UDPConnection udp;

    TextView resultTextV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        beerBtn = findViewById(R.id.beerButt);
        subBtn = findViewById(R.id.subButt);
        yogurtBtn = findViewById(R.id.yogurtButt);
        dogBtn = findViewById(R.id.hotDogButt);

        resultTextV = findViewById(R.id.resultTV);

        udp = new UDPConnection();
        udp.start();

        beerBtn.setOnClickListener(
                (view)->
                {
                    udp.sendMessage("1");
                }
        );

        subBtn.setOnClickListener(
                (view) ->
                {
                    udp.sendMessage("2");
                }
        );

        yogurtBtn.setOnClickListener(
                (view) ->
                {
                    udp.sendMessage("3");
                }
        );

        dogBtn.setOnClickListener(
                (view) ->
                {
                    udp.sendMessage("4");
                }
        );
    }
}