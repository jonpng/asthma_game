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

    int todayInt = (int) (new Date().getTime() / 1000);
    int lastLogin;

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

        lastLogin = sharedPref.getInt("lastLogin", 1023413456);
        daysApart = ((todayInt - lastLogin) / 86400); //86400
//        Log.d("PNG", Integer.toString(todayInt));
//        Log.d("PNG", Integer.toString(lastLogin));
//        Log.d("PNG", Integer.toString(daysApart));

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
                editor.commit();
            } else {
                intent = new Intent(SplashScreen.this, StoryActivity.class);
            }
//            Log.d("PNG", Integer.toString(sharedPref.getInt("loginStreak", 12)));
            SplashScreen.this.startActivity(intent);
        }
        return true;
    }
}
