package com.app4;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

@SuppressLint("Registered") public class SyncTwo extends AppCompatActivity
{
    ProgressBar pic, login, circle;
    Button sync;
    TextView tv;
    boolean truepic;
    boolean truelogin;
    Random rand=new Random();

    @Override protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sync_two);

        pic=findViewById(R.id.pb);
        login=findViewById(R.id.pb2);
        circle=findViewById(R.id.pb3);
        tv=findViewById(R.id.view2);
        sync=findViewById(R.id.sync);
        circle.setVisibility(View.INVISIBLE);
    }

    public void Sendit(View view)
    {
        circle.setVisibility(View.VISIBLE);
        DownloadTask task=new DownloadTask();
        LoginTask task2=new LoginTask();
        task.execute();
        task2.execute();
    }

    public void Returnit(View view) { startActivity(new Intent(SyncTwo.this,MainActivity.class)); }

    @SuppressLint("StaticFieldLeak") private class DownloadTask extends AsyncTask<Void,Integer,Boolean>
    {
        @Override protected void onPreExecute()
        {
            super.onPreExecute();
        }

        @Override protected void onPostExecute(Boolean aBoolean)
        {
            super.onPostExecute(aBoolean);
            truepic=aBoolean;
        }

        @Override protected void onProgressUpdate(Integer... values)
        {
            super.onProgressUpdate(values);
            pic.setProgress(values[0]);
        }

        @Override protected Boolean doInBackground(Void... voids)
        {
            int range=rand.nextInt(2)+3;

            for (int i=0; i<range; i++)
            {
                publishProgress((i*100)/range);
                try { Thread.sleep(500); }
                catch (InterruptedException e) { e.printStackTrace(); }
            }
            return true;
        }
    }

    @SuppressLint("StaticFieldLeak") private class LoginTask extends AsyncTask<Void,Integer,Boolean>
    {
        @Override protected Boolean doInBackground(Void... voids)
        {
            int range=rand.nextInt(3)+2;

            for (int i=0; i<range; i++)
            {
                publishProgress((i*100)/range);
                try { Thread.sleep(500); }
                catch (InterruptedException e) { e.printStackTrace(); }
            }
            return true;
        }

        @Override protected void onPreExecute() { super.onPreExecute(); }

        @Override protected void onPostExecute(Boolean aBoolean)
        {
            super.onPostExecute(aBoolean);
            circle.setVisibility(View.INVISIBLE);
            truelogin=aBoolean;
            tv.setTextColor(Color.BLUE);
            tv.setTextSize(100);
            tv.setGravity(Gravity.CENTER_HORIZONTAL);
            if(truepic&&truelogin) { tv.setText("Success!"); }
            else { tv.setText("Nah!"); }
        }

        @Override protected void onProgressUpdate(Integer... values)
        {
            super.onProgressUpdate(values);
            login.setProgress(values[0]);
        }
    }
}