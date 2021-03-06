package cs4605.asthmagame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainMenuActivity extends AppCompatActivity {
    private ImageButton playButton;
    private ImageButton rechargeButton;
    private ImageButton quizButton;
    private ImageButton shopButton;
    private ImageButton storyButton;
    private ImageButton settingsButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        playButton = (ImageButton) findViewById(R.id.imageButtonPlay);
        rechargeButton = (ImageButton) findViewById(R.id.imageButtonRecharge);
        quizButton = (ImageButton) findViewById(R.id.imageButtonQuiz);
        shopButton = (ImageButton) findViewById(R.id.imageButtonShop);
        storyButton = (ImageButton) findViewById(R.id.imageButtonStory);
        settingsButton = (ImageButton) findViewById(R.id.imageButtonSettings);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playGame();
            }
        });
        rechargeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recharge();
            }
        });
        quizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quiz();
            }
        });
        shopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shop();
            }
        });
        storyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                story();
            }
        });
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settings();
            }
        });
    }

    private void playGame() {
        playButton.setImageResource(R.drawable.button_selected);
        Intent activityIntent = new Intent(MainMenuActivity.this, LoadingActivity.class);
        startActivity(activityIntent);
        finish();
    }

    private void recharge() {
        DatabaseHandler db = new DatabaseHandler(this);
        int tryCount;
        tryCount = db.getTries();
        if (tryCount < 1) {
            db.updateTries(++tryCount);
        }
        db.close();
        rechargeButton.setImageResource(R.drawable.button_selected);
        Intent activityIntent = new Intent(MainMenuActivity.this, StartActivity.class);
        startActivity(activityIntent);
        finish();
    }

    private void quiz() {
        quizButton.setImageResource(R.drawable.button_selected);
        Intent activityIntent = new Intent(MainMenuActivity.this, QuizActivity.class);
        startActivity(activityIntent);
        finish();
    }

    private void shop() {
        shopButton.setImageResource(R.drawable.button_selected);
        Intent activityIntent = new Intent(MainMenuActivity.this, DailyLogin.class);
        startActivity(activityIntent);
        finish();
    }

    private void story() {
        storyButton.setImageResource(R.drawable.button_selected);
        Intent activityIntent = new Intent(MainMenuActivity.this, StoryActivity.class);
        startActivity(activityIntent);
        finish();
    }

    private void settings() {
        settingsButton.setImageResource(R.drawable.button_selected);
        Intent activityIntent = new Intent(MainMenuActivity.this, SplashScreen.class);
        startActivity(activityIntent);
        finish();
    }
    @Override
    public void onBackPressed() {
        finish();
    }

}
