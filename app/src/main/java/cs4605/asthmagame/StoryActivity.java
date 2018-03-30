package cs4605.asthmagame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.view.View;

public class StoryActivity extends AppCompatActivity {
    private ImageView story1;
    private ImageView story2;
    private ImageView story3;
    private ImageView story4;
    private int current;

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

    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();

        if (action == MotionEvent.ACTION_UP) {
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
                Intent intent = new Intent(StoryActivity.this, StartActivity.class);
                StoryActivity.this.startActivity(intent);
            }
            current ++;
        }

        return true;
    }

}
