package cs4605.asthmagame;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class StartActivity extends AppCompatActivity {

    private String participantPrefix;
    private ImageButton rechargeButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_start);

        participantPrefix = "4" + "_t_" + System.currentTimeMillis();;
        rechargeButton = (ImageButton) findViewById(R.id.rechargeButton);
        rechargeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchExperiment();
            }
        });

    }


    private void launchExperiment() {

        Intent activityIntent = new Intent(StartActivity.this, MainActivity.class);
        Bundle extras = new Bundle();
        extras.putString("prefix", participantPrefix);

        activityIntent.putExtras(extras);
        startActivity(activityIntent);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent activityIntent = new Intent(StartActivity.this, MainMenuActivity.class);
        startActivity(activityIntent);
        finish();
    }

}
