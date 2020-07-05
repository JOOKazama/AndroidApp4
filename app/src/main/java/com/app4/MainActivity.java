package com.app4;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

@SuppressLint("Registered")
public class MainActivity extends AppCompatActivity
{
    Button first_assignment, second_assignment;

    @Override protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        first_assignment=findViewById(R.id.first_assignment);
        second_assignment=findViewById(R.id.second_assignment);

        first_assignment.setOnClickListener(new View.OnClickListener()
        { @Override public void onClick(View v) { startActivity(new Intent(MainActivity.this,Query.class)); } });
        second_assignment.setOnClickListener(new View.OnClickListener()
        { @Override public void onClick(View v) { startActivity(new Intent(MainActivity.this,SyncTwo.class)); } });
    }
}