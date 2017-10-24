package lottery.in.matka.models;

import android.text.TextUtils;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.Date;

/**
 * Created by WarFly on 10/22/2017.
 */
@IgnoreExtraProperties
public class ChartItem {
    private String pattiOpen = "239";//Constant.EMPTY;
    private String pattiClose = "890";//Constant.EMPTY;
    private String singleOpen = "2";//Constant.EMPTY;
    private String singleClose = "7";//Constant.EMPTY;
    private boolean isMonday;
    private Date Date = new Date();

    public String getPattiOpen() {
        return pattiOpen;
    }

    public void setPattiOpen(String pattiOpen) {
        this.pattiOpen = pattiOpen;
    }

    public String getPattiClose() {
        return pattiClose;
    }

    public void setPattiClose(String pattiClose) {
        this.pattiClose = pattiClose;
    }

    public String getSingleOpen() {
        return singleOpen;
    }

    public void setSingleOpen(String singleOpen) {
        this.singleOpen = singleOpen;
    }

    public String getSingleClose() {
        return singleClose;
    }

    public void setSingleClose(String singleClose) {
        this.singleClose = singleClose;
    }

    public boolean isDoubling() {
        return (!TextUtils.isEmpty(singleClose) && (Integer.parseInt(singleOpen) - Integer.parseInt(singleClose) + 10) % 5 == 0);
    }
}
