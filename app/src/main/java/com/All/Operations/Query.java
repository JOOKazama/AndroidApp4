package com.All.Operations;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ProgressBar;
import com.All.Main.MainActivity;
import com.All.R;

@SuppressLint("Registered")
public class Query extends AppCompatActivity
{
    Button button_send, button_return;
    ProgressBar progress_bar;
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
        progress_bar=findViewById(R.id.progress_bar_new);
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

    public void Returnit(View view) { startActivity(new Intent(Query.this, MainActivity.class)); }

    @SuppressLint("StaticFieldLeak")
    class FirstAsyncTask extends AsyncTask<Integer, Integer, String>
    {

        @Override protected void onPreExecute()
        {
            super.onPreExecute();
            progress_bar.setVisibility(View.VISIBLE);
        }

        @Override protected void onPostExecute(String s)
        {
            super.onPostExecute(s);
            progress_bar.setVisibility(View.GONE);
            result.setText(s);
            result.setTextColor(Color.GREEN);
            result.setTextSize(100);
            result.setGravity(Gravity.CENTER_HORIZONTAL);
        }

        @Override protected void onProgressUpdate(Integer... values)
        {
            super.onProgressUpdate(values);
            progress_bar.setProgress(values[0]);
        }

        @Override protected String doInBackground(Integer... integers)
        {
            progress_bar.setMax(integers[0]);

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