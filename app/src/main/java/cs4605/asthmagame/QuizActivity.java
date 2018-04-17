package cs4605.asthmagame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class QuizActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
    }


    @Override
    public void onBackPressed() {
        Intent activityIntent = new Intent(QuizActivity.this, MainMenuActivity.class);
        startActivity(activityIntent);
        finish();
    }

}
