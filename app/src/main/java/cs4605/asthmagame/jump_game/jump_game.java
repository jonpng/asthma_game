package cs4605.asthmagame.jump_game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import android.graphics.*;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.media.*;
import android.util.*;
import android.view.MotionEvent;
import android.view.View;

import cs4605.asthmagame.R;

public class jump_game extends AppCompatActivity {

    Paint paint;
    Canvas canvas;
//    Timer timer;
    static int height,width;
    trig trig;
    player kid;
    public static final int g = 900,least=500;
    double total_up,score,bullet_dt;
    String msg;
    double lorr;
    MediaPlayer mediaPlayer;
    SoundPool soundPool;
    HashMap<String, Integer> hashMap;
    MediaPlayer hatPlayer,rocketPlayer;
    AudioManager audioManager;
    int maxVolume;
    boolean gameover,dead,gamestart,statistics,settings,pause,music_on,dir_shoot;
    int highest_score,last_score,last_jump,max_jump;
    int last_time,max_time,total_time,total_play,total_score;
    int this_jump;
    double this_time;
    double average_score;
    float mmx,mmy;

    int photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jump_game);
    }

    public jump_game() {
        Log.d("Game","Game constructor");
        paint=new Paint();
        canvas=null;
//        timer=new Timer();
//        setFrameRate(50);
        kid=new player(this);
        trig=null;
        //boards=null;bullets=null;
        msg=new String("click");
        soundPool=new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        hashMap=new HashMap<String, Integer>();
    }

    public void init() {
        Log.d("Game","Game.init");
        dir_shoot=true;
        music_on=true;
        another_init();
    }

    public void another_init() {
        trig=null;
        kid.init_player();
        bullet_dt=0;
        score=0;
        gameover=false;
        gamestart=false;
        dead=false;
        statistics=false;
        settings=false;
        pause=false;
        this_jump=0;
        this_time=0;
    }

    public void draw() {
        Log.d("Game","Game.draw");
        //paint.setColor(Color.WHITE);
        height=canvas.getHeight();
        width=canvas.getWidth();
        canvas.drawColor(Color.rgb(255, 182, 193));
        paint.setColor(Color.rgb(255, 105, 180));
        //paint.setColor(Color.BLUE);
        for (int i=0;i<=height;i+=20)
            canvas.drawLine(0, i, width, i, paint);
        for (int i=0;i<=width;i+=20)
            canvas.drawLine(i, 0, i, height, paint);

        Bitmap kidbm = BitmapFactory.decodeResource(getResources(), R.drawable.jamal_flying);
        kid.draw(canvas, paint, kidbm);
//        if (trig!=null) {
//            trig.draw(canvas, paint, photo);
//        }
    }
}
