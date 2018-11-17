package bango_000.mypdf;

import android.content.ClipData;
import android.view.View;

public class MyLongClickListner implements View.OnLongClickListener
{

    @Override
    public boolean onLongClick(View v)
    {

        ClipData dragdata = ClipData.newPlainText("","");

        View.DragShadowBuilder shdwbldr = new View.DragShadowBuilder(v);

        v.startDrag(dragdata, shdwbldr, v, 0);
        v.setVisibility(View.INVISIBLE);

        return true;
    }

}