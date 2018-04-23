package cs4605.asthmagame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.view.View;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.lang.*;

public class StoryActivity extends AppCompatActivity {
    private ImageView story1;
    private ImageView story2;
    private ImageView story3;
    private ImageView story4;
    private int current;
    private DatabaseHandler db = new DatabaseHandler(this);
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);
        story1 = findViewById(R.id.story_page1);
        story2 = findViewById(R.id.story_page2);
        story3 = findViewById(R.id.story_page3);
        story4 = findViewById(R.id.story_page4);
        current = 1;
        story1.setVisibility(View.VISIBLE);
        story2.setVisibility(View.INVISIBLE);
        story3.setVisibility(View.INVISIBLE);
        story4.setVisibility(View.INVISIBLE);
    }

    private void nextPage() {
        if (current == 1) {
            story2.setVisibility(View.VISIBLE);
            story1.setVisibility(View.INVISIBLE);
        } else if (current == 2) {
            story3.setVisibility(View.VISIBLE);
            story2.setVisibility(View.INVISIBLE);
        } else if (current == 3) {
            story4.setVisibility(View.VISIBLE);
            story3.setVisibility(View.INVISIBLE);
        } else {
            current = 0;
            String canisterDate = db.getSettingDate();
            Date lastCanisterDate = new Date();
            lastCanisterDate = new Date(lastCanisterDate.getTime() - 2 * 24 * 3600 * 1000);
            if (canisterDate != null) {
                try {
                    lastCanisterDate = sdf.parse(canisterDate);
                } catch (ParseException e) {
                    lastCanisterDate = new Date(lastCanisterDate.getTime() - 2 * 24 * 3600 * 1000);
                }
            }
            Date ctime = new Date();
            Intent intent;
            long diffDays = (ctime.getTime() - lastCanisterDate.getTime()) / (1000 * 60 * 60 * 24);
            int test = ((int) diffDays);
            if (test > 0) {
                int tryCount;
                tryCount = db.getTries();
                if (tryCount < 1) {
                    db.updateTries(++tryCount);
                }
                intent = new Intent(StoryActivity.this, StartActivity.class);
            } else {
                intent = new Intent(StoryActivity.this, MainMenuActivity.class);
                //intent = new Intent(StoryActivity.this, StartActivity.class);
            }
            StoryActivity.this.startActivity(intent);
            finish();
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();

        if (action == MotionEvent.ACTION_UP) {
            nextPage();
            current ++;
        }

        return true;
    }
    @Override
    public void onBackPressed() {
        Intent activityIntent = new Intent(StoryActivity.this, MainMenuActivity.class);
        startActivity(activityIntent);
        finish();
    }

}
