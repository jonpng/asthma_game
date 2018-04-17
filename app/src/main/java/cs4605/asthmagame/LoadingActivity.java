package cs4605.asthmagame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.content.Intent;
import android.os.Handler;

public class LoadingActivity extends AppCompatActivity {

    private DatabaseHandler db = new DatabaseHandler(this);
    private QuizFacts quizFact;
    private TextView textViewCategory;
    private TextView textViewFact;
    private static int SPLASH_TIME_OUT = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        quizFact = db.getQuizFact();
        textViewCategory = findViewById(R.id.textViewCategory);
        textViewFact = findViewById(R.id.textViewFact);

        textViewCategory.setText(quizFact.get_category());
        textViewFact.setText(quizFact.get_fact());


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // This method will be executed once the timer is over
                Intent i = new Intent(LoadingActivity.this, Main3Activity.class);
                startActivity(i);
                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);

    }
}
