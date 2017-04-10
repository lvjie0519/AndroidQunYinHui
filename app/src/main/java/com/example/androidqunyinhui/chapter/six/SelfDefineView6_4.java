package com.example.androidqunyinhui.chapter.six;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by lvjie on 2017/4/10 0010.
 */
public class SelfDefineView6_4 extends View{

    private Paint paintCircle;
    private int circleWidth = 400;

    private Paint paintDegree;

    private Paint paintHour;
    private Paint paintMinute;

    public SelfDefineView6_4(Context context) {
        super(context);

        init();
    }

    public SelfDefineView6_4(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public SelfDefineView6_4(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init(){

        paintCircle = new Paint();
        paintCircle.setStyle(Paint.Style.STROKE);
        paintCircle.setAntiAlias(true);             // 是否抗锯齿
        paintCircle.setStrokeWidth(5);

        paintDegree = new Paint();

        paintHour = new Paint();
        paintHour.setStrokeWidth(10);

        paintMinute = new Paint();
        paintMinute.setStrokeWidth(5);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 画时钟的圆
        // 绘制圆，参数一是中心点的x轴，参数二是中心点的y轴，参数三是半径，参数四是paint对象
        canvas.drawCircle(circleWidth/2, circleWidth/2, circleWidth/2, paintCircle);

        // 画刻度
        for(int i=0; i<24; i++){
            String degree = i+"";
            if(i==0 || i==6 || i==12 || i==18){
                paintDegree.setStrokeWidth(5);
                paintDegree.setTextSize(30);
                // 画线，参数一起始点的x轴位置，参数二起始点的y轴位置，参数三终点的x轴水平位置，参数四y轴垂直位置，最后一个参数为Paint 画刷对象。
                canvas.drawLine(circleWidth/2, 0, circleWidth/2, 60, paintDegree);
                // 渲染文本，Canvas类除了上面的还可以描绘文字，参数一是String类型的文本，参数二x轴，参数三y轴，参数四是Paint对象。
                canvas.drawText(degree, circleWidth/2-paintDegree.measureText(degree)/2, 90, paintDegree);
            }else{
                paintDegree.setStrokeWidth(3);
                paintDegree.setTextSize(15);
                canvas.drawLine(circleWidth/2, 0, circleWidth/2, 30, paintDegree);
                canvas.drawText(degree, circleWidth/2-paintDegree.measureText(degree)/2, 60, paintDegree);
            }

            canvas.rotate(15, circleWidth/2, circleWidth/2);  // 坐标旋转
        }

        // 画时钟和分钟
        canvas.save();    // 保存之前已绘制的图像即画布
        canvas.translate(circleWidth/2, circleWidth/2);         // 画布平移，相当于坐标原点平移
        canvas.drawLine(0,0,40,40, paintHour);
        canvas.drawLine(0,0,40,100, paintMinute);
        canvas.restore();  // 可以理解为ps中的合并图层，作用是将save之后绘制的与save之前绘制的图像进行合并;

        //  以上的save  restore  能够让坐标抽又恢复到原始状态；
//        canvas.drawCircle(circleWidth/2, circleWidth/2, circleWidth/2, paintCircle);
    }
}
