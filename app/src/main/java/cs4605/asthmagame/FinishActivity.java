package cs4605.asthmagame;

import android.app.Notification;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FinishActivity extends AppCompatActivity {

    private String participantPrefix;
    private String participantScore;
    private Date startDate;
    private ImageButton startButton;
    private Button redoButton;
    private TextView scoreText;
    private ImageView imageViewRed;
    private ImageView imageViewYellow;
    private ImageView imageViewGreen;
    private ImageView imageRedLvl;
    private ImageView imageRedLvl2;
    private ImageView imageRedLvl3;
    private ImageView imageYellowLvl;
    private ImageView imageYellowLvl2;
    private ImageView imageYellowLvl3;
    private ImageView imageYellowLvl4;
    private ImageView imageYellowLvl5;
    private ImageView imageYellowLvl6;
    private ImageView imageYellowLvl7;
    private ImageView imageGreenLvl;
    private ImageView imageGreenLvl2;
    private ImageView imageGreenLvl3;
    private ImageView imageGreenLvl4;
    private ImageView imageGreenLvl5;
    private ImageView imageGreenLvl6;
    private ImageView imageGreenLvl7;
    private ImageView imageGreenLvl8;
    private ImageView imageGreenLvl9;
    private ImageView imageGreenLvl10;
    private DatabaseHandler db = new DatabaseHandler(this);
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
        participantPrefix = getIntent().getStringExtra("prefix");
        participantScore = getIntent().getStringExtra("score");
        String participantDate = getIntent().getStringExtra("startDate");

        try {
            startDate = sdf.parse(participantDate);
        } catch (ParseException e) {
            startDate = new Date();
        }

        startButton = (ImageButton) findViewById(R.id.startButton);
        redoButton = (Button) findViewById(R.id.buttonRedo);
        scoreText = (TextView) findViewById(R.id.scoreText);

        if (participantScore != null){
            scoreText.setText(participantScore);
        } else {
            participantScore = "-1";
            scoreText.setText(participantScore);
        }
        int score = Integer.parseInt(participantScore);

        imageViewRed = (ImageView) findViewById(R.id.imageViewRed);
        imageViewYellow = (ImageView) findViewById(R.id.imageViewYellow);
        imageViewGreen = (ImageView) findViewById(R.id.imageViewGreen);
        imageRedLvl = (ImageView) findViewById(R.id.imageRedLvl);
        imageRedLvl2 = (ImageView) findViewById(R.id.imageRedLvl2);
        imageRedLvl3 = (ImageView) findViewById(R.id.imageRedLvl3);
        imageYellowLvl = (ImageView) findViewById(R.id.imageYellowLvl);
        imageYellowLvl2 = (ImageView) findViewById(R.id.imageYellowLvl2);
        imageYellowLvl3 = (ImageView) findViewById(R.id.imageYellowLvl3);
        imageYellowLvl4 = (ImageView) findViewById(R.id.imageYellowLvl4);
        imageYellowLvl5 = (ImageView) findViewById(R.id.imageYellowLvl5);
        imageYellowLvl6 = (ImageView) findViewById(R.id.imageYellowLvl6);
        imageYellowLvl7 = (ImageView) findViewById(R.id.imageYellowLvl7);
        imageGreenLvl = (ImageView) findViewById(R.id.imageGreenLvl);
        imageGreenLvl2 = (ImageView) findViewById(R.id.imageGreenLvl2);
        imageGreenLvl3 = (ImageView) findViewById(R.id.imageGreenLvl3);
        imageGreenLvl4 = (ImageView) findViewById(R.id.imageGreenLvl4);
        imageGreenLvl5 = (ImageView) findViewById(R.id.imageGreenLvl5);
        imageGreenLvl6 = (ImageView) findViewById(R.id.imageGreenLvl6);
        imageGreenLvl7 = (ImageView) findViewById(R.id.imageGreenLvl7);
        imageGreenLvl8 = (ImageView) findViewById(R.id.imageGreenLvl8);
        imageGreenLvl9 = (ImageView) findViewById(R.id.imageGreenLvl9);
        imageGreenLvl10 = (ImageView) findViewById(R.id.imageGreenLvl10);

        imageViewRed.setVisibility(View.INVISIBLE);
        imageViewYellow.setVisibility(View.INVISIBLE);
        imageViewGreen.setVisibility(View.INVISIBLE);
        imageRedLvl.setVisibility(View.INVISIBLE);
        imageRedLvl2.setVisibility(View.INVISIBLE);
        imageRedLvl3.setVisibility(View.INVISIBLE);
        imageYellowLvl.setVisibility(View.INVISIBLE);
        imageYellowLvl2.setVisibility(View.INVISIBLE);
        imageYellowLvl3.setVisibility(View.INVISIBLE);
        imageYellowLvl4.setVisibility(View.INVISIBLE);
        imageYellowLvl5.setVisibility(View.INVISIBLE);
        imageYellowLvl6.setVisibility(View.INVISIBLE);
        imageGreenLvl.setVisibility(View.INVISIBLE);
        imageGreenLvl2.setVisibility(View.INVISIBLE);
        imageGreenLvl3.setVisibility(View.INVISIBLE);
        imageGreenLvl4.setVisibility(View.INVISIBLE);
        imageGreenLvl5.setVisibility(View.INVISIBLE);
        imageGreenLvl6.setVisibility(View.INVISIBLE);
        imageGreenLvl7.setVisibility(View.INVISIBLE);
        imageGreenLvl8.setVisibility(View.INVISIBLE);
        imageGreenLvl9.setVisibility(View.INVISIBLE);
        imageGreenLvl10.setVisibility(View.INVISIBLE);


        if (score < 45) {
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, "Score")
                    .setContentTitle(getResources().getString(R.string.score_notification_title))
                    .setSmallIcon(R.drawable.lung)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setStyle(new NotificationCompat.BigTextStyle());

            if (score < 17) {
                mBuilder.setContentText(getResources().getString(R.string.score_notification_red_threshold));
            } else {
                mBuilder.setContentText(getResources().getString(R.string.score_notification_yellow_threshold));
            }

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            notificationManager.notify(score, mBuilder.build());
        }

        if (score < 4) {
            imageViewRed.setVisibility(View.VISIBLE);
            imageRedLvl.setVisibility(View.VISIBLE);
        } else if (score < 10) {
            imageViewRed.setVisibility(View.VISIBLE);
            imageRedLvl.setVisibility(View.VISIBLE);
            imageRedLvl2.setVisibility(View.VISIBLE);
        } else if (score < 17) {
            imageViewRed.setVisibility(View.VISIBLE);
            imageRedLvl.setVisibility(View.VISIBLE);
            imageRedLvl2.setVisibility(View.VISIBLE);
            imageRedLvl3.setVisibility(View.VISIBLE);
        } else if (score < 24) {
            imageViewYellow.setVisibility(View.VISIBLE);
            imageYellowLvl.setVisibility(View.VISIBLE);
            imageYellowLvl2.setVisibility(View.VISIBLE);
            imageYellowLvl3.setVisibility(View.VISIBLE);
            imageYellowLvl4.setVisibility(View.VISIBLE);
        } else if (score < 31) {
            imageViewYellow.setVisibility(View.VISIBLE);
            imageYellowLvl.setVisibility(View.VISIBLE);
            imageYellowLvl2.setVisibility(View.VISIBLE);
            imageYellowLvl3.setVisibility(View.VISIBLE);
            imageYellowLvl4.setVisibility(View.VISIBLE);
            imageYellowLvl5.setVisibility(View.VISIBLE);
       } else if (score < 38) {
            imageViewYellow.setVisibility(View.VISIBLE);
            imageYellowLvl.setVisibility(View.VISIBLE);
            imageYellowLvl2.setVisibility(View.VISIBLE);
            imageYellowLvl3.setVisibility(View.VISIBLE);
            imageYellowLvl4.setVisibility(View.VISIBLE);
            imageYellowLvl5.setVisibility(View.VISIBLE);
            imageYellowLvl6.setVisibility(View.VISIBLE);
        } else if (score < 45) {
            imageViewYellow.setVisibility(View.VISIBLE);
            imageYellowLvl.setVisibility(View.VISIBLE);
            imageYellowLvl2.setVisibility(View.VISIBLE);
            imageYellowLvl3.setVisibility(View.VISIBLE);
            imageYellowLvl4.setVisibility(View.VISIBLE);
            imageYellowLvl5.setVisibility(View.VISIBLE);
            imageYellowLvl6.setVisibility(View.VISIBLE);
            imageYellowLvl7.setVisibility(View.VISIBLE);
        } else if (score < 52) {
            imageViewGreen.setVisibility(View.VISIBLE);
            imageGreenLvl.setVisibility(View.VISIBLE);
            imageGreenLvl2.setVisibility(View.VISIBLE);
            imageGreenLvl3.setVisibility(View.VISIBLE);
            imageGreenLvl4.setVisibility(View.VISIBLE);
            imageGreenLvl5.setVisibility(View.VISIBLE);
            imageGreenLvl6.setVisibility(View.VISIBLE);
            imageGreenLvl7.setVisibility(View.VISIBLE);
            imageGreenLvl8.setVisibility(View.VISIBLE);
        } else if (score < 59) {
            imageViewGreen.setVisibility(View.VISIBLE);
            imageGreenLvl.setVisibility(View.VISIBLE);
            imageGreenLvl2.setVisibility(View.VISIBLE);
            imageGreenLvl3.setVisibility(View.VISIBLE);
            imageGreenLvl4.setVisibility(View.VISIBLE);
            imageGreenLvl5.setVisibility(View.VISIBLE);
            imageGreenLvl6.setVisibility(View.VISIBLE);
            imageGreenLvl7.setVisibility(View.VISIBLE);
            imageGreenLvl8.setVisibility(View.VISIBLE);
            imageGreenLvl9.setVisibility(View.VISIBLE);

        } else {
            imageViewGreen.setVisibility(View.VISIBLE);
            imageGreenLvl.setVisibility(View.VISIBLE);
            imageGreenLvl2.setVisibility(View.VISIBLE);
            imageGreenLvl3.setVisibility(View.VISIBLE);
            imageGreenLvl4.setVisibility(View.VISIBLE);
            imageGreenLvl5.setVisibility(View.VISIBLE);
            imageGreenLvl6.setVisibility(View.VISIBLE);
            imageGreenLvl7.setVisibility(View.VISIBLE);
            imageGreenLvl8.setVisibility(View.VISIBLE);
            imageGreenLvl9.setVisibility(View.VISIBLE);
            imageGreenLvl10.setVisibility(View.VISIBLE);
        }

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGame();
            }
        });

        redoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redo();
            }
        });

    }

    private void startGame() {
        db.addCanister(new Canister(startDate, Integer.parseInt(participantScore)));
        Intent activityIntent = new Intent(FinishActivity.this, MainMenuActivity.class);
        startActivity(activityIntent);
        finish();
    }

    public void redo() {
        Intent activityIntent = new Intent(FinishActivity.this, StartActivity.class);
        startActivity(activityIntent);
        finish();
    }
    @Override
    public void onBackPressed() {
        redo();
    }


}
