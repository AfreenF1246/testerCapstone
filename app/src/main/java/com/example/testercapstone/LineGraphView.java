package com.example.testercapstone;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.graphics.Color;
import android.graphics.Path;

public class LineGraphView extends View {
    private Paint paint;
    private float[] dataPoints; // Array of data points

    public LineGraphView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
    }

    public void setData(float[] data) {
        this.dataPoints = data;
        invalidate(); // Redraw the view with new data
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (dataPoints == null || dataPoints.length < 2) return;

        float width = getWidth();
        float height = getHeight();
        float deltaX = width / (dataPoints.length - 1);

        Path path = new Path();
        path.moveTo(0, height - dataPoints[0]);
        for (int i = 1; i < dataPoints.length; i++) {
            path.lineTo(i * deltaX, height - dataPoints[i]);
        }

        canvas.drawPath(path, paint);
    }
}

