package bango_000.mypdf;

import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class MyDragListener implements View.OnDragListener
{

    int x, y;
    private RelativeLayout.LayoutParams params;

    @Override
    public boolean onDrag(View v, DragEvent event)
    {
        View view = (View) event.getLocalState();


        switch(event.getAction())
        {
            case DragEvent.ACTION_DRAG_STARTED:

                params = (RelativeLayout.LayoutParams) view.getLayoutParams();
                break;

            case DragEvent.ACTION_DRAG_ENTERED:
                x = (int) event.getX();
                y = (int) event.getY();

                break;

            case DragEvent.ACTION_DRAG_EXITED :
                x = (int) event.getX();
                y = (int) event.getY();
                params.leftMargin = x;
                params.topMargin = y;
                view.setLayoutParams(params);

                break;
/*
            case DragEvent.ACTION_DRAG_LOCATION  :
                x=  (int) event.getX();
                y =  (int) event.getY();
                break;
*/
            case DragEvent.ACTION_DRAG_ENDED   :

                break;

            case DragEvent.ACTION_DROP:

                x = (int) event.getX();
                y = (int) event.getY();

                //v.setX(x);
                //v.setY(y);

                x = (int) event.getX();
                y = (int) event.getY();
                params.leftMargin = x;
                params.topMargin = y;

                view.setLayoutParams(params);
                view.setVisibility(View.VISIBLE);

                //ViewGroup owner = (ViewGroup) view.getParent();
                //owner.removeView(view);
                //RelativeLayout container = (RelativeLayout) v;
                //container.addView(view);
                view.setVisibility(View.VISIBLE);

                break;
            default: break;
        }
        return true;
    }


}