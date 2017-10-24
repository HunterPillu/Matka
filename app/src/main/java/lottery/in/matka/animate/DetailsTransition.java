package lottery.in.matka.animate;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.transition.ChangeBounds;
import android.transition.ChangeImageTransform;
import android.transition.ChangeTransform;
import android.transition.TransitionSet;

/**
 * Created by WarFly on 5/7/2017.
 */
@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class DetailsTransition extends TransitionSet {
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public DetailsTransition() {
        setOrdering(ORDERING_TOGETHER);
        addTransition(new ChangeBounds()).
                addTransition(new ChangeTransform()).
                addTransition(new ChangeImageTransform());
    }
}