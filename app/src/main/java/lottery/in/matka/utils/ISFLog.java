package lottery.in.matka.utils;

import android.util.Log;


public class ISFLog {

    private static final String TAG = "ISF";


    public static void e(String tag, String message) {
        Log.e(tag, "" + message);
    }

    public static void e(String tag, String message, Exception e) {
        Log.e(tag, "" + message, e);
    }

    public static void e(Exception e) {
        Log.e(TAG, Log.getStackTraceString(e));
    }

    public static void e(Error e) {
        Log.e(TAG, Log.getStackTraceString(e));
    }

    public static void e(NullPointerException e) {
        Log.e(TAG, Log.getStackTraceString(e));
    }

    public static void d(String tag, String message) {
        if (null != message) {
            Log.d(tag, "" + message);
        }
    }

    public static void info(String tag, String message) {
        Log.i(tag, "" + message);
    }
}
