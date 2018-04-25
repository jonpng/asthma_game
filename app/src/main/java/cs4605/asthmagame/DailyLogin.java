package cs4605.asthmagame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cs4605.asthmagame.jump_game.jump_game;

public class DailyLogin extends AppCompatActivity {

    private DatabaseHandler db = new DatabaseHandler(this);
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daily_login);

        ImageView day = (ImageView) findViewById(R.id.day);
        ImageView day1 = (ImageView) findViewById(R.id.day1);
        ImageView day2 = (ImageView) findViewById(R.id.day2);
        ImageView day3 = (ImageView) findViewById(R.id.day3);
        ImageView day4 = (ImageView) findViewById(R.id.day4);

        TextView dayt = (TextView) findViewById(R.id.dayt);
        TextView dayt1 = (TextView) findViewById(R.id.dayt1);
        TextView dayt2 = (TextView) findViewById(R.id.dayt2);
        TextView dayt3 = (TextView) findViewById(R.id.dayt3);
        TextView dayt4 = (TextView) findViewById(R.id.dayt4);

        ImageView lt = (ImageView) findViewById(R.id.locktick);
        ImageView lt1 = (ImageView) findViewById(R.id.locktick1);
        ImageView lt2 = (ImageView) findViewById(R.id.locktick2);
        ImageView lt3 = (ImageView) findViewById(R.id.locktick3);
        ImageView lt4 = (ImageView) findViewById(R.id.locktick4);

        TextView coint = (TextView) findViewById(R.id.coint);
        TextView coint1 = (TextView) findViewById(R.id.coint1);
        TextView coint2 = (TextView) findViewById(R.id.coint2);
        TextView coint3 = (TextView) findViewById(R.id.coint3);
        TextView coint4 = (TextView) findViewById(R.id.coint4);

        TextView plus = (TextView) findViewById(R.id.plus);
        TextView plus1 = (TextView) findViewById(R.id.plus1);
        TextView plus2 = (TextView) findViewById(R.id.plus2);
        TextView plus3 = (TextView) findViewById(R.id.plus3);
        TextView plus4 = (TextView) findViewById(R.id.plus4);

        plus.setText("");
        plus1.setText("");
        plus2.setText("");
        plus3.setText("");
        plus4.setText("");

        ImageView lun = (ImageView) findViewById(R.id.lung);
        ImageView lun1 = (ImageView) findViewById(R.id.lung1);
        ImageView lun2 = (ImageView) findViewById(R.id.lung2);
        ImageView lun3 = (ImageView) findViewById(R.id.lung3);
        ImageView lun4 = (ImageView) findViewById(R.id.lung4);

        lun.setImageResource(0);
        lun1.setImageResource(0);
        lun2.setImageResource(0);
        lun3.setImageResource(0);
        lun4.setImageResource(0);

        SharedPreferences sharedPref = this.getSharedPreferences("mainPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        int streak = sharedPref.getInt("loginStreak", 1);
        int earn = (streak > 5) ? 50 : streak * 10;


        if (streak == 2) {
            day.setImageResource(R.drawable.day);
            day1.setImageResource(R.drawable.today);

            dayt.setText("1");
            dayt1.setText("");

            lt1.setImageResource(R.drawable.tick);
        }

        if (streak > 2) {
            int c = streak - 2;
            int c1 = streak - 1;
            int c3 = streak + 1;
            int c4 = streak + 2;

            if (c % 7 == 0) {
                plus.setText("+");
                lun.setImageResource(R.drawable.lungfin);
            }
            if (c1 % 7 == 0) {
                plus1.setText("+");
                lun1.setImageResource(R.drawable.lungfin);
            }
            if (streak % 7 == 0) {
                plus2.setText("+");
                lun2.setImageResource(R.drawable.lungfin);
            }
            if (c3 % 7 == 0) {
                plus3.setText("+");
                lun3.setImageResource(R.drawable.lungfin);
            }
            if (c4 % 7 == 0) {
                plus4.setText("+");
                lun4.setImageResource(R.drawable.lungfin);
            }

            day.setImageResource(R.drawable.day);
            day1.setImageResource(R.drawable.day);
            day2.setImageResource(R.drawable.today);

            lt1.setImageResource(R.drawable.tick);
            lt2.setImageResource(R.drawable.tick);

            dayt.setText(Integer.toString(c));
            dayt1.setText(Integer.toString(c1));
            dayt2.setText("");
            dayt3.setText(Integer.toString(c3));
            dayt4.setText(Integer.toString(c4));
            coint.setText(Integer.toString((c > 5) ? 50 : c * 10));
            coint1.setText(Integer.toString((c1 > 5) ? 50 : c1 * 10));
            coint2.setText(Integer.toString(earn));
            coint3.setText(Integer.toString((c3 > 5) ? 50 : c3 * 10));
            coint4.setText(Integer.toString((c4 > 5) ? 50 : c4 * 10));
        }
        editor.putInt("coins", sharedPref.getInt("coins", 0) + earn);
//        editor.putInt("coins", 0); // RESET
        editor.commit();
        Log.d("PNG", Integer.toString(sharedPref.getInt("coins", 12)));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();

        if (action == MotionEvent.ACTION_UP) {
            // the following checks the code to see when the last time the ma-ma was done.
            // if it was greater than a day, the story line is played, if not the main menu is displayed
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
            Intent intent;
            long diffDays = (ctime.getTime()- lastCanisterDate.getTime()) / (1000 * 60 * 60 * 24);
            int test = ((int) diffDays);
            if (test > 0) {

                intent = new Intent(DailyLogin.this, StoryActivity.class);
            } else {
                intent = new Intent(DailyLogin.this, MainMenuActivity.class);
                //intent = new Intent(StoryActivity.this, StartActivity.class);
            }

            //Intent intent = new Intent(DailyLogin.this, StoryActivity.class);
            DailyLogin.this.startActivity(intent);
            db.close();
            finish();
        }
        return true;
    }
    @Override
    public void onBackPressed() {
        Intent activityIntent = new Intent(DailyLogin.this, MainMenuActivity.class);
        startActivity(activityIntent);
        db.close();
        finish();
    }

}
