package cs4605.asthmagame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;

import cs4605.asthmagame.jump_game.jump_game;

public class DailyLogin extends AppCompatActivity {

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

        SharedPreferences sharedPref = this.getSharedPreferences("mainPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        int streak = sharedPref.getInt("loginStreak", 1);

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

            day.setImageResource(R.drawable.day);
            day1.setImageResource(R.drawable.day);
            day2.setImageResource(R.drawable.today);

            lt1.setImageResource(R.drawable.tick);
            lt2.setImageResource(R.drawable.tick);

            int earn = (streak > 5) ? 50 : streak * 10;

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

            editor.putInt("coins", earn);
            editor.commit();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();

        if (action == MotionEvent.ACTION_UP) {
            Intent intent = new Intent(DailyLogin.this, StoryActivity.class);
            DailyLogin.this.startActivity(intent);
        }
        return true;
    }
}
