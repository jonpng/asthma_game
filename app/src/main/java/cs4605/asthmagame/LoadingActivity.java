package cs4605.asthmagame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.content.Intent;
import android.os.Handler;

public class LoadingActivity extends AppCompatActivity {

    private DatabaseHandler db = new DatabaseHandler(this);
    private QuizFacts quizFact;
    private TextView textViewFact;
    private static int SPLASH_TIME_OUT = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        // Retrieve a quiz fact from the sqlite database to display on the screen
        quizFact = db.getQuizFact();
        if (quizFact == null) {
            // load a default fact if the database could not be queried for any reason
            quizFact = new QuizFacts("test", "Continuous cough, chest tightness and chest pain are symptoms of an Asthma attack.");
        }

        textViewFact = findViewById(R.id.textViewFact);

        textViewFact.setText(quizFact.get_fact());

        // Delay for the screen to close
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
