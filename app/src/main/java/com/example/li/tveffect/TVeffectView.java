package com.example.li.tveffect;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

public class TVeffectView extends View {

    int[] mColors;

    Paint mPaint;

    public TVeffectView(Context context) {
        super(context);

        //初始化Paint
        init();
    }

    public TVeffectView(Context context, AttributeSet attrs) {
        super(context, attrs);


        //初始化Paint
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < getMeasuredHeight(); i+=3) {
            for (int j = 0; j < getMeasuredWidth(); j+=3) {
                mPaint.setColor(mColors[(int)(Math.random()*7)]);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    canvas.drawOval(j, i, j+3, i+3, mPaint);
                }
            }
        }

        postInvalidateDelayed(100);


    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mColors = new int[]{
                getResources().getColor(R.color.WhiteSmoke),
                getResources().getColor(R.color.Gainsboro),
                getResources().getColor(R.color.LightGrey),
                getResources().getColor(R.color.Sliver),
                getResources().getColor(R.color.DarkGray),
                getResources().getColor(R.color.Gray),
                getResources().getColor(R.color.DimGray),
        };
    }
}
