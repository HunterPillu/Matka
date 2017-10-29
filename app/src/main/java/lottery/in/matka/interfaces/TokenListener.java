package lottery.in.matka.interfaces;

import java.util.List;

import lottery.in.matka.models.ChartItem;
import lottery.in.matka.models.UserToken;

/**
 * Created by desktop on 29-10-2017.
 */

public interface TokenListener {

    void onSuccess(String code, List<UserToken> response);

    void onFailure(Exception e);
}
