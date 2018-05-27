package tracker.album.com.br.albumtracker.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import tracker.album.com.br.albumtracker.R;

/**
 * Created by Leonardo Assunção on 04/04/2016.
 */
public class SplashActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int SPLASH_TIME_OUT = 2000;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
            Intent i = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(i);

            finish();
        }
    }, SPLASH_TIME_OUT);
    }
}


