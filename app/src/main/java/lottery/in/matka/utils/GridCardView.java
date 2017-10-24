package lottery.in.matka.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class GridCardView extends CardView {
    public GridCardView(Context context) {
        super(context);
    }

    public GridCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GridCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth());
    }
}
