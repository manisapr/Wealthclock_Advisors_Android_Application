package android.wealthclockadvisors.app.wealthclockadvisors.Views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

import wealthclockadvisors.app.wealthclockadvisors.R;


public class ProgressCircle extends android.support.v7.widget.AppCompatImageView  {

    Paint paint;
    private  int progress;


    public ProgressCircle(Context context) {
        super(context);
    }

    public ProgressCircle(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint=new Paint();

        TypedArray a=context.getTheme().obtainStyledAttributes(attrs, R.styleable.ProgressCircle,0,0);

        try {
            progress=a.getInteger(R.styleable.ProgressCircle_progress_circle,0);
        }
        finally {
            a.recycle();
        }


    }

    public ProgressCircle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawMainCircle(canvas);
        drawProgress(canvas,getProgress());
        drawLines(canvas);
        secondaryCircle(canvas);
    }


    public  void drawMainCircle(Canvas canvas)
    {
        paint.setColor(Color.parseColor("#4b4b5b"));
        paint.setAntiAlias(true);
        double centreX=getWidth()/2;
        double centreY=getHeight()/2;
        double radius=Math.min(getHeight(),getWidth())/2.5;
        canvas.drawCircle((float) centreX,(float) centreY,(float)radius,paint);
    }


    public void  drawLines(Canvas canvas)
    {
        paint.setColor(Color.parseColor("#353544"));
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        double centreX=getWidth()/2;
        double centreY=getHeight()/2;
        double radius=Math.min(getHeight(),getWidth())/2.5;
        double angle=Double.valueOf( (double) 360/(double) 7);


        double x1=centreX + (radius*(Math.cos(Math.toRadians(0))));
        double y1=centreY + (radius*(Math.sin(Math.toRadians(0))));
        double x2=centreX + (radius*(Math.cos(Math.toRadians(angle))));
        double y2=centreY + (radius*(Math.sin(Math.toRadians(angle))));
        double x3=centreX+(radius*(Math.cos(Math.toRadians(angle*2))));
        double y3=centreY+(radius*(Math.sin(Math.toRadians(angle*2))));
        double x4=centreX+(radius*(Math.cos(Math.toRadians(angle*3))));
        double y4=centreY+(radius*(Math.sin(Math.toRadians(angle*3))));
        double x5=centreX+(radius*(Math.cos(Math.toRadians(angle*4))));
        double y5=centreY+(radius*(Math.sin(Math.toRadians(angle*4))));
        double x6=centreX+(radius*(Math.cos(Math.toRadians(angle*5))));
        double y6=centreY+(radius*(Math.sin(Math.toRadians(angle*5))));
        double x7=centreX+(radius*(Math.cos(Math.toRadians(angle*6))));
        double y7=centreY+(radius*(Math.sin(Math.toRadians(angle*6))));

        canvas.drawLine((float)centreX,(float) centreY,(float) x1,(float) y1,paint);
        canvas.drawLine((float)centreX,(float) centreY,(float) x2,(float) y2,paint);
        canvas.drawLine((float)centreX,(float) centreY,(float) x3,(float) y3,paint);
        canvas.drawLine((float)centreX,(float) centreY,(float) x4,(float) y4,paint);
        canvas.drawLine((float)centreX,(float) centreY,(float) x5,(float) y5,paint);
        canvas.drawLine((float)centreX,(float) centreY,(float) x6,(float) y6,paint);
        canvas.drawLine((float)centreX,(float) centreY,(float) x7,(float) y7,paint);
    }





    public void secondaryCircle(Canvas canvas)
    {
        paint.setColor(Color.parseColor("#353544"));
        paint.setAntiAlias(true);
        int centreX=getWidth()/2;
        int centreY=getHeight()/2;
        double radius=Math.min(getWidth(),getHeight())/6;
        canvas.drawCircle(centreX,centreY,(float)radius,paint);
    }

    public void drawProgress(Canvas canvas,int progress){

        double centreX=getWidth()/2;
        double centreY=getHeight()/2;
        double radius=Math.min(getHeight(),getWidth())/2.5;
        double angle=Double.valueOf( (double) 360/(double) 7);

        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor("#13a097"));
        final RectF rectF=new RectF();
        rectF.set((float) (centreX-radius),(float) (centreY-radius),(float) (centreX+radius),(float) (centreY+radius));
        canvas.drawArc(rectF,0,(float) (angle*(double)progress),true,paint);




    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        //update value
        this.progress = progress;
        //redraw
        invalidate();
        requestLayout();
    }
}
