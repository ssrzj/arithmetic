package com.sz.ssr.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.shenzhen.baselib.utils.LogUtil;

public class ScoreWidget extends View {
    // 默认五边形
    private int angleCount=7;
    // 默认数据类型个数
    private int dataCount=2;
    // 中心到定点的距离
    private float radius=200;

    private int netCount = 4;
    // 默认角度
    private float degree=(float) 360/angleCount;


    private Paint mPaintLine;
    public ScoreWidget(Context context) {
        this(context,null);
    }

    public ScoreWidget(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public ScoreWidget(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawLine(canvas);
    }
    // mPaintLine  绘制线条
    private void drawLine(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        int centerPointX = width/2;
        int centerPointY = height/2;

        double cos = Math.cos(Math.toRadians(degree));
        double sin = Math.sin(Math.toRadians(degree));
//
        Path path = new Path();
        path.moveTo((float) (centerPointX+radius*cos),(float) (centerPointY-radius*sin));
        path.lineTo((float)centerPointX+radius,(float)centerPointY);
        path.lineTo((float)(centerPointX+radius*cos),(float)(centerPointY+radius*sin));
        path.lineTo((float)(centerPointX-radius*cos),(float)(centerPointY+radius*sin));
        path.lineTo((float)centerPointX-radius,(float)centerPointY);
        path.lineTo((float)(centerPointX-radius*cos),(float) (centerPointY-radius*sin));
//        path.close();
//
//          canvas.drawLine((float) (centerPointX+radius*cos),(float) (centerPointY-radius*sin),centerPointX+radius,centerPointY,mPaintLine);
//          canvas.drawLine((float) (centerPointX+radius),(float) (centerPointY),(float) (centerPointX+radius*cos),(float)(centerPointY+radius*sin),mPaintLine);
//          canvas.drawLine((float) (centerPointX+radius*cos),(float)(centerPointY+radius*sin),(float)(centerPointX-radius*cos),(float)(centerPointY+radius*sin),mPaintLine);
//          canvas.drawLine((float)(centerPointX-radius*cos),(float)(centerPointY+radius*sin),(float)centerPointX-radius,(float)centerPointY,mPaintLine);
//          canvas.drawLine((float)centerPointX-radius,(float)centerPointY,(float)(centerPointX-radius*cos),(float) (centerPointY-radius*sin),mPaintLine);
//          canvas.drawLine((float)(centerPointX-radius*cos),(float) (centerPointY-radius*sin),(float) (centerPointX+radius*cos),(float) (centerPointY-radius*sin),mPaintLine);

//          radius= radius*3/2;
//
//        canvas.drawLine((float) (centerPointX+radius*cos),(float) (centerPointY-radius*sin),centerPointX+radius,centerPointY,mPaintLine);
//        canvas.drawLine((float) (centerPointX+radius),(float) (centerPointY),(float) (centerPointX+radius*cos),(float)(centerPointY+radius*sin),mPaintLine);
//        canvas.drawLine((float) (centerPointX+radius*cos),(float)(centerPointY+radius*sin),(float)(centerPointX-radius*cos),(float)(centerPointY+radius*sin),mPaintLine);
//        canvas.drawLine((float)(centerPointX-radius*cos),(float)(centerPointY+radius*sin),(float)centerPointX-radius,(float)centerPointY,mPaintLine);
//        canvas.drawLine((float)centerPointX-radius,(float)centerPointY,(float)(centerPointX-radius*cos),(float) (centerPointY-radius*sin),mPaintLine);
//        canvas.drawLine((float)(centerPointX-radius*cos),(float) (centerPointY-radius*sin),(float) (centerPointX+radius*cos),(float) (centerPointY-radius*sin),mPaintLine);

//        radius= radius*3;
//
//        canvas.drawLine((float) (centerPointX+radius*cos),(float) (centerPointY-radius*sin),centerPointX+radius,centerPointY,mPaintLine);
//        canvas.drawLine((float) (centerPointX+radius),(float) (centerPointY),(float) (centerPointX+radius*cos),(float)(centerPointY+radius*sin),mPaintLine);
//        canvas.drawLine((float) (centerPointX+radius*cos),(float)(centerPointY+radius*sin),(float)(centerPointX-radius*cos),(float)(centerPointY+radius*sin),mPaintLine);
//        canvas.drawLine((float)(centerPointX-radius*cos),(float)(centerPointY+radius*sin),(float)centerPointX-radius,(float)centerPointY,mPaintLine);
//        canvas.drawLine((float)centerPointX-radius,(float)centerPointY,(float)(centerPointX-radius*cos),(float) (centerPointY-radius*sin),mPaintLine);
//        canvas.drawLine((float)(centerPointX-radius*cos),(float) (centerPointY-radius*sin),(float) (centerPointX+radius*cos),(float) (centerPointY-radius*sin),mPaintLine);


        canvas.drawLine((float)(centerPointX+radius*cos),(float)(centerPointY-radius*sin),(float)(centerPointX-radius*cos),(float)(centerPointY+radius*sin),mPaintLine);
        canvas.drawLine((float) (centerPointX+radius),(float) (centerPointY),(float)centerPointX-radius,(float)centerPointY,mPaintLine);
        canvas.drawLine((float) (centerPointX+radius*cos),(float)(centerPointY+radius*sin),(float)(centerPointX-radius*cos),(float) (centerPointY-radius*sin),mPaintLine);


        canvas.drawPath(path,mPaintLine);
//
//        float perRadius = 0;
//        for(int i=0;i<netCount;i++){
//            perRadius = radius*i/netCount;
//        }


//        drawMultShape(canvas,angleCount,radius,1);
//        drawMultShape(canvas,angleCount,radius,2);
//        drawMultShape(canvas,angleCount,radius,3);

    }

    public void drawMultShape(Canvas canvas,int count,float radius,int numCount){
        canvas.translate(radius,radius);
        Path path = new Path();
        if(count<5){
            return;
        }
        for (int i=0;i<count;i++){
            if (i==0){
                path.moveTo(radius*cos(360/count*i)*numCount/3,radius*sin(360/count*i)*numCount/3);//绘制起点
            }else{
                path.lineTo(radius*cos(360/count*i)*numCount/3,radius*sin(360/count*i)*numCount/3);
            }
        }
        mPaintLine.setStrokeWidth(3);
        mPaintLine.setStyle(Paint.Style.STROKE);
        path.close();
        mPaintLine.setColor(Color.GREEN);
        canvas.drawPath(path,mPaintLine);
    }

    float sin(int num){
        return (float) Math.sin(num*Math.PI/180);
    }
    float cos(int num){
        return (float) Math.cos(num*Math.PI/180);
    }


    private void initPaint() {
        mPaintLine = new Paint();
        mPaintLine.setColor(Color.GREEN);
        mPaintLine.setAntiAlias(true);
        mPaintLine.setStrokeWidth(5f);



    }
}
