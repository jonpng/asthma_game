package cs4605.asthmagame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.animation.ValueAnimator;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;
import android.hardware.SensorEvent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEventListener;
import android.util.Log;


public class Main3Activity extends AppCompatActivity implements SensorEventListener {

    SensorManager sensorManager;
    Sensor mSensor;
    ImageView kid;
    ImageView circle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        sensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
        mSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        sensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);


        SharedPreferences sharedPref = this.getSharedPreferences("mainPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        final ImageView backgroundOne = (ImageView) findViewById(R.id.background_one);
        final ImageView backgroundTwo = (ImageView) findViewById(R.id.background_two);
        kid = (ImageView) findViewById(R.id.kid);

        // add a way to exit
        circle = (ImageView) findViewById(R.id.circle);
        circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exitGame();
            }
        });

        // TO DO: Get use breath to get speed from breath test
        //        Multiply speed by factor (tbd)

        final ValueAnimator animator = ValueAnimator.ofFloat(0.0f, 1.0f);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(2000L);    // PUT FACTOR HERE, LOWER IS FASTER
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                final float progress = (float) animation.getAnimatedValue();
                final float height = backgroundOne.getHeight();
                final float width = backgroundOne.getWidth();
                final float translationY = height * progress;
                backgroundOne.setTranslationY(translationY);
                backgroundTwo.setTranslationY(translationY - height);
            }
        });
        animator.start();

        int coins = sharedPref.getInt("coins", 0);

        TextView coint = (TextView) findViewById(R.id.coint);
        coint.setText(Integer.toString(coins));

    }

    private double pitch, tilt, azimuth;

    @Override
    public void onSensorChanged(SensorEvent event) {
        //Get Rotation Vector Sensor Values
        double[] g = convertFloatsToDoubles(event.values.clone());

        //Normalise
        double norm = Math.sqrt(g[0] * g[0] + g[1] * g[1] + g[2] * g[2] + g[3] * g[3]);
        g[0] /= norm;
        g[1] /= norm;
        g[2] /= norm;
        g[3] /= norm;

        //Set values to commonly known quaternion letter representatives
        double x = g[0];
        double y = g[1];
        double z = g[2];
        double w = g[3];

        //Calculate Pitch in degrees (-180 to 180)
        double sinP = 2.0 * (w * x + y * z);
        double cosP = 1.0 - 2.0 * (x * x + y * y);
        pitch = Math.atan2(sinP, cosP) * (180 / Math.PI);

        //Calculate Tilt in degrees (-90 to 90)
        double sinT = 2.0 * (w * y - z * x);
        if (Math.abs(sinT) >= 1)
            tilt = Math.copySign(Math.PI / 2, sinT) * (180 / Math.PI);
        else
            tilt = Math.asin(sinT) * (180 / Math.PI);

        //Calculate Azimuth in degrees (0 to 360; 0 = North, 90 = East, 180 = South, 270 = West)
        double sinA = 2.0 * (w * z + x * y);
        double cosA = 1.0 - 2.0 * (y * y + z * z);
        azimuth = Math.atan2(sinA, cosA) * (180 / Math.PI);

        Log.d("PNG", "tile: " + tilt);
//        Log.d("PNG", "azimuth" + azimuth);
//        Log.d("PNG", "pitch" + pitch);
        kid.setTranslationX((float) tilt * 5);

    }

    private double[] convertFloatsToDoubles(float[] input)
    {
        if (input == null)
            return null;

        double[] output = new double[input.length];

        for (int i = 0; i < input.length; i++)
            output[i] = input[i];

        return output;
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void exitGame() {
        Intent intent = new Intent(Main3Activity.this, MainMenuActivity.class);
        Main3Activity.this.startActivity(intent);
        finish();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onBackPressed() {
        Intent activityIntent = new Intent(Main3Activity.this, MainMenuActivity.class);
        startActivity(activityIntent);
        finish();
    }


}
