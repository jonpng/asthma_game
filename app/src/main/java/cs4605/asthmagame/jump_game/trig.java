package cs4605.asthmagame.jump_game;

import java.util.*;
import android.graphics.*;

/**
 * Created by jonpng on 4/6/18.
 */

public class trig {
    double x,y,speed;
    private boolean dir;
    player kid;
    static final int trig_length=80,trig_height=90;
    Rect rect;
    Rect trect;
    jump_game game;
    Bitmap bitmap;


    public trig(jump_game gamething,player kidthing, int id) {
        rect=new Rect();
        trect=new Rect();
        kid=kidthing;
        this.game=gamething;
        x=(double)new Random().nextDouble()*(jump_game.width-trig_length);
        y=-10-trig_height;
        speed=0;
        while (speed<70)
            speed=(double)new Random().nextInt(130);
        dir=true;
        trect.set(65,188,103,237);
        bitmap = BitmapFactory.decodeResource(null, id);
    }

    boolean get_dir() {
        rect.set((int)x,(int)(y-49),(int)(x+trig_length),(int)y);
        return dir;
    }

    void go(double dt) {
        if (x>0 && x<game.width-trig_length)
        {
            x+=speed*dt;
            return;
        }
        speed=-speed;
        dir=(speed>0)?true:false;
        while (x>game.width-trig_length || x<0)
            x+=speed*dt;
        if (!dir) trect.set(106,188,144,237);
        else trect.set(65,188,103,237);
    }

    void down(double dt) {
        y+=dt;
    }

    void draw(Canvas canvas,Paint paint,Bitmap bitmap) {
        rect.set((int)x,(int)y-trig_height,(int)(x+trig_length),(int)y);
        canvas.drawBitmap(bitmap, trect, rect, paint);
    }
}
