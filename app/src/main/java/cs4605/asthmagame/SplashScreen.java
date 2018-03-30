package cs4605.asthmagame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import java.util.Date;

public class SplashScreen extends AppCompatActivity {

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    Date todayDate = new Date();
    int todayInt = (int) todayDate.getTime() / 1000;
    int lastLogin;
    Date lastDate;

    int daysApart;

    private void checkFeaturesAndPermissions() {

        for (String s : Config.APP_PERMISSIONS) {
            int permissionCheck = ContextCompat.checkSelfPermission(getApplicationContext(), s);
            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                Log.i("location", "no permission");
                ActivityCompat.requestPermissions(this, Config.APP_PERMISSIONS, 1);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        checkFeaturesAndPermissions();

        sharedPref = this.getSharedPreferences("mainPrefs", Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        lastLogin = sharedPref.getInt("lastLogin", 1);
        lastDate = new Date((long) lastLogin * 1000L);
        daysApart = (int)((todayDate.getTime() - lastDate.getTime()) / (1000*60*60*24l));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();

        if (action == MotionEvent.ACTION_UP) {
            Intent intent;
            if (daysApart >= 1) {
                if (daysApart == 1) {
                    editor.putInt("loginStreak", sharedPref.getInt("loginStreak", 1) + 1);
                } else {
                    editor.putInt("loginStreak", 1);
                }
                intent = new Intent(SplashScreen.this, DailyLogin.class);
                editor.putInt("lastLogin", todayInt);
            } else {
                intent = new Intent(SplashScreen.this, MainActivity.class);
            }
            SplashScreen.this.startActivity(intent);
        }
        return true;
    }
}
