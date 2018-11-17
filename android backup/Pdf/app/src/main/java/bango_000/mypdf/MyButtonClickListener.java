package bango_000.mypdf;

import android.view.View;
import android.view.ViewGroup;

public class MyButtonClickListener implements View.OnClickListener
{

    @Override
    public void onClick(View v)
    {
        ViewGroup relativeParent = (ViewGroup) v.getParent();
        CustomEdittext edttxt = new CustomEdittext(v.getContext());
        relativeParent.addView(edttxt);
        edttxt.setOnLongClickListener(new MyLongClickListner());
        edttxt.setText("changez la");

    }

}
