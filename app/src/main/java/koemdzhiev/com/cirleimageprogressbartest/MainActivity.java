package koemdzhiev.com.cirleimageprogressbartest;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    public CircleProgressBar progressBar;
    private static final String TAG = MainActivity.class.getSimpleName();

    private ObjectAnimator progressAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        progressBar = (CircleProgressBar)findViewById(R.id.custom_progressBar);
        progressAnimator = ObjectAnimator.ofFloat(progressBar, "progress", 0.0f, 100.0f);
        progressAnimator.setDuration(1200);

        ImageButton imageButton = (ImageButton)findViewById(R.id.imageButton);
        imageButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {

                    progressAnimator.start();
                    Log.d(TAG, "task executed");
                }

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    progressAnimator.cancel();
                    Log.d(TAG, "task canceled");
                }


                return false;
            }
        });

    }

}
