package lottery.in.matka.models;

import android.text.TextUtils;

import com.google.firebase.database.IgnoreExtraProperties;

import lottery.in.matka.utils.Constant;

/**
 * Created by WarFly on 10/22/2017.
 */
@IgnoreExtraProperties
public class ChartItem {
    private String pattiOpen = Constant.EMPTY;
    private String pattiClose = Constant.EMPTY;
    private String singleOpen = Constant.EMPTY;
    private String singleClose = Constant.EMPTY;
    private boolean firstDayOfWeek;
    private String date;
    private long timeStamp;

    public boolean isFirstDayOfWeek() {
        return firstDayOfWeek;
    }

    public void setFirstDayOfWeek(boolean firstDayOfWeek) {
        this.firstDayOfWeek = firstDayOfWeek;
    }

    /* public String isMonday() {
                return isMonday;
            }

            public void addMonday(boolean monday) {
                isMonday = "" + monday;
            }

            public boolean checkIfMonday() {
                return Boolean.parseBoolean(isMonday);
            }

            public void setMonday(String monday) {
                isMonday = monday;
            }
        */
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

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
