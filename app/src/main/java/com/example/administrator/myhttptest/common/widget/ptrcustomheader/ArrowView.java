package com.example.administrator.myhttptest.common.widget.ptrcustomheader;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

import com.example.administrator.myhttptest.R;


/**
 *
 */
public class ArrowView extends View {


    private static final int DEFAULT_SIZE = 50;
    private static final int STROKE_WIDTH = 3;
    private static final double ARROW_HEIGHT_PERCENT = 0.6;
    private static final float ALL_SWPEE_ANGEL = 350;
    private Context mContext;
    float mSwpeeAngle = 5;
    private Paint mArrowPaint;
    private Paint mRingPaint;
    private boolean isRunning = false;
    private Handler mHandler = new Handler();
    private  float mStartAngle = -90;


    public ArrowView(Context context) {
        this(context,null);
    }

    public ArrowView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ArrowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initPaint();
    }

    private void initPaint() {
        mArrowPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mArrowPaint.setStyle(Paint.Style.STROKE);
        mArrowPaint.setColor(mContext.getResources().getColor(R.color.textColor_Gray_Tip));
        mArrowPaint.setStrokeWidth(STROKE_WIDTH);

        mRingPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRingPaint.setStyle(Paint.Style.STROKE);
        mRingPaint.setColor(mContext.getResources().getColor(R.color.textColor_Gray_Tip));
        mRingPaint.setStrokeWidth(STROKE_WIDTH);

    }




    //处理wrap_content的情况
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);

        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);

        if(widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST ){
            setMeasuredDimension(DEFAULT_SIZE,DEFAULT_SIZE);
        }else if(widthMeasureSpec == MeasureSpec.AT_MOST){
            setMeasuredDimension(heightSpecSize,heightSpecSize);
        }else if(heightSpecMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(widthSpecSize,widthSpecSize);
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawRing(canvas);

    }


    private void drawRing(Canvas canvas){

        int height = getHeight();
        int width = getWidth();

        RectF rect = new RectF(STROKE_WIDTH,STROKE_WIDTH,width-STROKE_WIDTH,height-STROKE_WIDTH);
        canvas.drawArc(rect, mStartAngle, mSwpeeAngle, false, mRingPaint);

        int arrowStartY = (int)((1-ARROW_HEIGHT_PERCENT)/2*getHeight());
        int arrowEndY = (int)(arrowStartY+ARROW_HEIGHT_PERCENT*height);
        if( (-mSwpeeAngle) < ALL_SWPEE_ANGEL && !isRunning ) {
            drawArrow(canvas, getWidth() / 2, arrowStartY, getWidth() / 2, arrowEndY);
        }
    }


    private void drawArrow(Canvas canvas, int sx, int sy, int ex, int ey){
        double H = 8; // 箭头高度
        double L = 8; // 底边的一半
        int x3 = 0;
        int y3 = 0;
        int x4 = 0;
        int y4 = 0;
        double awrad = Math.atan(L / H); // 箭头角度
        double arraow_len = Math.sqrt(L * L + H * H); // 箭头的长度
        double[] arrXY_1 = rotateVec(ex - sx, ey - sy, awrad, true, arraow_len);
        double[] arrXY_2 = rotateVec(ex - sx, ey - sy, -awrad, true, arraow_len);
        double x_3 = ex - arrXY_1[0]; // (x3,y3)是第一端点
        double y_3 = ey - arrXY_1[1];
        double x_4 = ex - arrXY_2[0]; // (x4,y4)是第二端点
        double y_4 = ey - arrXY_2[1];
        Double X3 = new Double(x_3);
        x3 = X3.intValue();
        Double Y3 = new Double(y_3);
        y3 = Y3.intValue();
        Double X4 = new Double(x_4);
        x4 = X4.intValue();
        Double Y4 = new Double(y_4);
        y4 = Y4.intValue();
        // 画线
        canvas.drawLine(sx, sy, ex, ey, mArrowPaint);
        Path triangle = new Path();
        triangle.moveTo(ex, ey);
        triangle.lineTo(x3, y3);
        triangle.moveTo(ex, ey);
        triangle.lineTo(x4, y4);
        // triangle.close();
        canvas.drawPath(triangle,mArrowPaint);
    }

    // 计算
    public double[] rotateVec(int px, int py, double ang, boolean isChLen, double newLen)
    {
        double mathstr[] = new double[2];
        // 矢量旋转函数，参数含义分别是x分量、y分量、旋转角、是否改变长度、新长度
        double vx = px * Math.cos(ang) - py * Math.sin(ang);
        double vy = px * Math.sin(ang) + py * Math.cos(ang);
        if (isChLen) {
            double d = Math.sqrt(vx * vx + vy * vy);
            vx = vx / d * newLen;
            vy = vy / d * newLen;
            mathstr[0] = vx;
            mathstr[1] = vy;
        }
        return mathstr;
    }

    public void onArrowAngelChanged(double percent){
        if(percent>1){
            percent = 1;
        }
        mSwpeeAngle = -(float)(percent*ALL_SWPEE_ANGEL);
        //L.d("mSwpeeAngle: "+ mSwpeeAngle);
        invalidate();
    }



    public void startAnim() {
        isRunning = true;
        mSwpeeAngle = ALL_SWPEE_ANGEL;
        mHandler.post(mAnimationTask);
    }


    private Runnable mAnimationTask = new Runnable() {
        @Override
        public void run() {
            if(isRunning) {
                mStartAngle-=4;
                if (mStartAngle <= -ALL_SWPEE_ANGEL) {
                    mStartAngle = 0;
                }
                postInvalidate();
                mHandler.postDelayed(this, 20);
            }
        }
    };

    public void stopAnim() {
        isRunning = false;
        mHandler.removeCallbacks(mAnimationTask);
    }


    public boolean isRunning() {
        return isRunning;
    }




    public float getStartAngle() {
        return mStartAngle;
    }

    public void setStartAngle(float mStartAngle) {
        this.mStartAngle = mStartAngle;
        invalidate();
    }

    public float getSwpeeAngle() {
        return mSwpeeAngle;
    }

    public void setSwpeeAngle(float mSwpeeAngle) {
        this.mSwpeeAngle = mSwpeeAngle;
        invalidate();
    }
}
