package bango_000.mypdf;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

public class CustomCheckox extends android.support.v7.widget.AppCompatCheckBox{

    Paint paint;

    public CustomCheckox(Context context){
        super(context);
        init();
    }

    public CustomCheckox(Context context, AttributeSet attr){
        super(context, attr);
        init();
    }

    public void init(){
        paint = new Paint();

        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(4);
        paint.setColor(Color.BLUE);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        canvas.drawRect(0, 0, getWidth()/10, getHeight()/10, paint);
    }
}
