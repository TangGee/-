package com.fujin.splashanimation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by tangtang on 15/6/2.
 */
public class SplashingView extends View {

    private int width;
    private int height;

    private Paint paint;
    private int currentStart=0;

    private  boolean round=false;

    private  int degree=0;

    private int radius=200;

    private boolean isEnd=false;

    private OnFinishListener onFinishListener;

    public interface OnFinishListener{
        void onFinish();

    }

    public void setOnFinishListener(OnFinishListener listener)
    {
        this.onFinishListener=listener;
    }



    private int[] colors={
            Color.YELLOW,
            Color.RED,
            Color.BLUE,
            Color.BLACK,
            Color.CYAN,
            Color.MAGENTA
    };

    public SplashingView(Context context) {
        super(context);
        init();
    }



    public SplashingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SplashingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    private void init() {

        paint=new Paint();
        paint.setAntiAlias(true);
        paint.setColor(colors[currentStart]);
    }

    public void startRount()
    {
        round=true;
        invalidate();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int cricleX=width/2;
        int cricleY=height/2;


        canvas.save();

        canvas.translate(cricleX, cricleY);
        canvas.rotate(degree);

        for (int i=0;i<colors.length;i++)
        {
            currentStart++;
            canvas.drawCircle(0, -radius, 20,paint);
            canvas.rotate(360 / colors.length);
            paint.setColor(colors[currentStart%colors.length]);

        }


        canvas.restore();

        if (round)
            startRound(canvas);

        if (isEnd)
            finish();

    }



    public void startRound(Canvas canvas)
    {
        if (isEnd)
        {
            throw new IllegalStateException("已经结束");
        }
        round=true;
        degree+=10%360;
        postDelayed(animationRunnable,200);
    }


    public void finish()
    {

        isEnd=true;


        round=false;

        radius-=10;

        if (radius==0)
        {

            if (onFinishListener!=null)
                onFinishListener.onFinish();
            return;
        }

        invalidate();
    }


    private Runnable animationRunnable=new Runnable() {
        @Override
        public void run() {
            if (round){
                invalidate();
            }
        }
    };


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        width=getWidth();
        height=getHeight();
    }
}
