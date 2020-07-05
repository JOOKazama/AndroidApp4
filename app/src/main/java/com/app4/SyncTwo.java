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

@SuppressLint("Registered")
public class SyncTwo extends AppCompatActivity
{
    ProgressBar progress_bar, progress_bar1, progress_bar2;
    Button button_check;
    TextView result;
    boolean boolean_picture;
    boolean boolean_login;
    Random rand=new Random();

    @Override protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sync_two);

        progress_bar=findViewById(R.id.progress_bar_new);
        progress_bar1=findViewById(R.id.progress_bar1);
        progress_bar2=findViewById(R.id.progress_bar2);
        result=findViewById(R.id.result);
        button_check=findViewById(R.id.button_check);
        progress_bar2.setVisibility(View.INVISIBLE);
    }

    public void Sendit(View view)
    {
        progress_bar2.setVisibility(View.VISIBLE);
        DownloadTask task=new DownloadTask();
        LoginTask task2=new LoginTask();
        task.execute();
        task2.execute();
    }

    public void Returnit(View view) { startActivity(new Intent(SyncTwo.this,MainActivity.class)); }

    @SuppressLint("StaticFieldLeak")
    private class DownloadTask extends AsyncTask<Void, Integer, Boolean>
    {
        @Override protected void onPreExecute()
        {
            super.onPreExecute();
        }

        @Override protected void onPostExecute(Boolean aBoolean)
        {
            super.onPostExecute(aBoolean);
            boolean_picture=aBoolean;
        }

        @Override protected void onProgressUpdate(Integer... values)
        {
            super.onProgressUpdate(values);
            progress_bar.setProgress(values[0]);
        }

        @Override protected Boolean doInBackground(Void... voids)
        {
            int range=rand.nextInt(2)+3;

            for(int i=0; i<range; i++)
            {
                publishProgress((i*100)/range);
                try { Thread.sleep(500); }
                catch(InterruptedException e) { e.printStackTrace(); }
            }

            return(Math.random()<0.5);
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class LoginTask extends AsyncTask<Void, Integer, Boolean>
    {
        @Override protected Boolean doInBackground(Void... voids)
        {
            int range=rand.nextInt(3)+2;

            for (int i=0; i<range; i++)
            {
                publishProgress((i*100)/range);
                try { Thread.sleep(500); }
                catch(InterruptedException e) { e.printStackTrace(); }
            }

            return(Math.random()<0.5);
        }

        @Override protected void onPreExecute() { super.onPreExecute(); }

        @Override protected void onPostExecute(Boolean aBoolean)
        {
            super.onPostExecute(aBoolean);
            progress_bar2.setVisibility(View.INVISIBLE);
            boolean_login=aBoolean;
            result.setTextColor(Color.BLUE);
            result.setTextSize(100);
            result.setGravity(Gravity.CENTER_HORIZONTAL);
            if(boolean_picture && boolean_login) { result.setText("Success!"); }
            else { result.setText("Nah!"); }
        }

        @Override protected void onProgressUpdate(Integer... values)
        {
            super.onProgressUpdate(values);
            progress_bar1.setProgress(values[0]);
        }
    }
}