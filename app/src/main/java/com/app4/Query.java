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
    Button button_send, button_return;
    EditText number;
    TextView result;

    @Override protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.query);

        button_send=findViewById(R.id.button_send);
        number=findViewById(R.id.number);
        result=findViewById(R.id.result);
        button_return=findViewById(R.id.button_return);
    }

    public void Sendit(View view)
    {
        if(TextUtils.isEmpty(number.getText().toString()))
        { Toast.makeText(Query.this, "Enter value!", Toast.LENGTH_SHORT).show(); }
        else
        {
            FirstAsyncTask first_async_task=new FirstAsyncTask();
            first_async_task.execute(Integer.parseInt(number.getText().toString()));
        }
    }

    public void Returnit(View view) { startActivity(new Intent(Query.this,MainActivity.class)); }

    @SuppressLint("StaticFieldLeak")
    class FirstAsyncTask extends AsyncTask<Integer,Integer,String>
    {
        ProgressDialog progress_dialog;

        @Override protected void onPreExecute()
        {
            super.onPreExecute();
            progress_dialog=ProgressDialog.show(Query.this, "Counting down: ", "Don't be in a hurry!");
        }

        @Override protected void onPostExecute(String s)
        {
            super.onPostExecute(s);
            progress_dialog.dismiss();
            number.setText(s);
            number.setTextColor(Color.GREEN);
            number.setTextSize(100);
            number.setGravity(Gravity.CENTER_HORIZONTAL);
        }

        @Override protected void onProgressUpdate(Integer... values)
        {
            super.onProgressUpdate(values);
            progress_dialog.setMessage(values[0]+" sec.");
        }

        @Override protected String doInBackground(Integer... integers)
        {
            for(int i=integers[0]; i>0; i--)
            {
                publishProgress((i));
                try { Thread.sleep(500); }
                catch(InterruptedException e) {e.printStackTrace(); }
            }

            return "Done!";
        }
    }
}