package com.app4;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

@SuppressLint("Registered") public class Query extends AppCompatActivity
{
    Button button, return1;
    EditText text;
    TextView view;

    @Override protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.query);

        button=findViewById(R.id.button);
        text=findViewById(R.id.text);
        view=findViewById(R.id.view);
        return1=findViewById(R.id.return1);
    }

    public void Sendit(View view)
    {
        if(TextUtils.isEmpty(text.getText().toString()))
        { Toast.makeText(Query.this, "Enter value!", Toast.LENGTH_SHORT).show(); }
        else
        {
            FirstAsyncTask task = new FirstAsyncTask();
            task.execute(Integer.parseInt(text.getText().toString()));
        }
    }

    public void Returnit(View view) { startActivity(new Intent(Query.this,MainActivity.class)); }

    @SuppressLint("StaticFieldLeak") class FirstAsyncTask extends AsyncTask<Integer,Integer,String>
    {
        ProgressDialog pg;

        @Override protected void onPreExecute()
        {
            super.onPreExecute();
            pg = ProgressDialog.show(Query.this, "Counting down: ", "Don't be in a hurry!");
        }

        @Override protected void onPostExecute(String s)
        {
            super.onPostExecute(s);
            pg.dismiss();
            view.setText(s);
            view.setTextColor(Color.GREEN);
            view.setTextSize(100);
            view.setGravity(Gravity.CENTER_HORIZONTAL);
        }

        @Override protected void onProgressUpdate(Integer... values)
        {
            super.onProgressUpdate(values);
            pg.setMessage(values[0]+" sec.");
        }

        @Override protected String doInBackground(Integer... integers)
        {
            for(int i=integers[0];i>0;i--)
            {
                publishProgress((i));
                try { Thread.sleep(500); }
                catch (InterruptedException e) {e.printStackTrace(); }
            }
            return "Done!";
        }
    }
}