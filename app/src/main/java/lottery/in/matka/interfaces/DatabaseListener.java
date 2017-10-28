package lottery.in.matka.interfaces;

import java.util.List;

import lottery.in.matka.models.ChartItem;

/**
 * Created by WarFly on 10/24/2017.
 */

public interface DatabaseListener {
    void onSuccess(String code, List<ChartItem> response);

    void onFailure(Exception e);
}
