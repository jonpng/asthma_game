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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SplashScreen extends AppCompatActivity {

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    private DatabaseHandler db = new DatabaseHandler(this);
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

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
        daysApart = ((todayInt - lastLogin)); //86400
        Log.d("PNG", Integer.toString(todayInt));
        Log.d("PNG", Integer.toString(lastLogin));
        Log.d("PNG", Integer.toString(daysApart));

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();

        if (action == MotionEvent.ACTION_UP) {
            Intent intent;
            if (daysApart >= 1) {
                if (daysApart >= 1) {
                    editor.putInt("loginStreak", sharedPref.getInt("loginStreak", 0) + 1);
                } else {
                    editor.putInt("loginStreak", 1);
                }
                intent = new Intent(SplashScreen.this, DailyLogin.class);
                editor.putInt("lastLogin", todayInt);
                editor.commit();
            } else {
                String canisterDate = db.getSettingDate();
                Date lastCanisterDate = new Date();
                lastCanisterDate = new Date(lastCanisterDate.getTime() - 2 * 24 * 3600 * 1000 );
                if (canisterDate != null) {
                    try {
                        lastCanisterDate = sdf.parse(canisterDate);
                    } catch (ParseException e) {
                        lastCanisterDate = new Date(lastCanisterDate.getTime() - 2 * 24 * 3600 * 1000 );
                    }
                }
                Date ctime = new Date();
                long diffDays = (ctime.getTime()- lastCanisterDate.getTime()) / (1000 * 60 * 60 * 24);
                int test = ((int) diffDays);
                if (test > 0) {
                    intent = new Intent(SplashScreen.this, StoryActivity.class);
                } else {
                    intent = new Intent(SplashScreen.this, MainMenuActivity.class);
                    //intent = new Intent(StoryActivity.this, StartActivity.class);
                }

                //intent = new Intent(SplashScreen.this, MainMenuActivity.class);
                //intent = new Intent(SplashScreen.this, DailyLogin.class);
            }
            Log.d("PNG", Integer.toString(sharedPref.getInt("loginStreak", 12)));
            SplashScreen.this.startActivity(intent);
            finish();
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        finish();
    }

}
