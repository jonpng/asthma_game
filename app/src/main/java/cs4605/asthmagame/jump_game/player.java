package cs4605.asthmagame.jump_game;

import android.graphics.*;
import android.graphics.drawable.Drawable;


/**
 * Created by jonpng on 4/6/18.
 */

public class player {
    double x,vx,y;
    public static final int length=100;
    boolean dir;
    boolean move;
    jump_game game;
    int speed;


    public player(jump_game gamething) {
        game = gamething;
        init_player();
    }

    public void init_player() {
        x = 210;vx=0;y=210;dir=true;
        speed=600;
        move=false;
    }

    void set_move(boolean _move) {move=_move;}

    void go_x(double dt,int width) {
        x+=vx*dt;
        if (x>width-length/2)
            x=-length/2;
        if (x<-length/2)
            x=width-length/2;
    }

    void go_vx(boolean _dir,double dt) {
        dir=_dir;
        if (dir)
        {
            if (vx<=0) vx+=5*speed*dt;
            vx+=speed*dt;
        }
        else
        {
            if (vx>=0) vx-=5*speed*dt;
            vx-=speed*dt;
        }
    }

    void draw(Canvas canvas,Paint paint,Bitmap bitmap) {
        canvas.drawBitmap(bitmap, new Rect(0, 0, 124, 120), new Rect((int)x, (int)y-96, (int)x+96, (int)y), paint);
    }
}
