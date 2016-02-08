package koemdzhiev.com.cirleimageprogressbartest;

import android.animation.ObjectAnimator;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public CircleProgressBar progressBar;
    private static final String TAG = MainActivity.class.getSimpleName();
    private BackgroundAsyncTask backgroundAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        progressBar = (CircleProgressBar)findViewById(R.id.custom_progressBar);

        backgroundAsyncTask = new BackgroundAsyncTask();

        ImageButton imageButton = (ImageButton)findViewById(R.id.imageButton);
        imageButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {

                    if (!backgroundAsyncTask.isCancelled()) {
                        backgroundAsyncTask.execute();
                        Log.d(TAG, "task executed");
                    }else {
                        backgroundAsyncTask.cancel(true);
                        progressBar.setProgress(0);
                    }


//                    Toast.makeText(MainActivity.this, "onTouch", Toast.LENGTH_LONG).show();
                }

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    backgroundAsyncTask.cancel(true);
                    progressBar.setProgress(0);
                    backgroundAsyncTask = new BackgroundAsyncTask();
                    Log.d(TAG, "task canceled");
                }


                return false;
            }
        });

    }

    public class BackgroundAsyncTask extends
            AsyncTask<Void, Integer, Void> {

        int myProgress;

        @Override
        protected void onPostExecute(Void result) {
            // TODO Auto-generated method stub
            progressBar.setProgress(0);
            Log.d(TAG, "onPostExecure. setProgres to 0");
        }

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
//            Toast.makeText(MainActivity.this, "onPreExecute", Toast.LENGTH_LONG).show();
            myProgress = 0;
        }

        @Override
        protected Void doInBackground(Void... params) {
            // TODO Auto-generated method stub
            while(myProgress<100){
                myProgress+=2.8;
                publishProgress(myProgress);
                SystemClock.sleep(1);
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            // TODO Auto-generated method stub
            progressBar.setProgress(values[0]);
        }

    }
}
