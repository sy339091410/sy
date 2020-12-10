package com.sun.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private Button button_hello;
    private Button button_name;
    List<Drawable> draws = new ArrayList<>();
    Integer num = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.tiger);
        button_hello = findViewById(R.id.button_hello);
        button_name = findViewById(R.id.button_name);
        draws.add(getResources().getDrawable(R.drawable.tiger));
        draws.add(getResources().getDrawable(R.drawable.tiger2));
        draws.add(getResources().getDrawable(R.drawable.tiger3));

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                drawable = getResources().getDrawable(R.drawable.tiger);
                imageView.setImageDrawable(draws.get(num));
                num++;
                if (num == 3){
                    num =0;
                }
                System.out.println(num);
            }
        });
        button_hello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"hello",Toast.LENGTH_SHORT).show();
            }
        });

        button_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setClass(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });


    }
}