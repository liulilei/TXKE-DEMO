package androiddesk.com.txke_demo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import androiddesk.com.txke_demo.util.Uiutils;

/**
 * @Description:
 * @author: lll
 * @date: 2019-04-24
 */
public class CircleView1 extends View {
    public CircleView1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private float radius = Uiutils.INSTANCE.dip2px(50f);

    public float getRadius() {
        Log.e("get1", radius + "");
        int i =  0xff;
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
        Log.e("set1", radius + "");
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(getWidth() / 2f, getHeight() / 2f, radius, paint);
    }
}
