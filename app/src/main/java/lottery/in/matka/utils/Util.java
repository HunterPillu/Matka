package lottery.in.matka.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Util {
    private static final String KEY_SIMPLE_DATE_FORMAT = "SimpleDateFormat";

    public Util() {
    }

    public static String getCurrentdate(String dateFormat) {
        String dateCurrent = "";
        try {
            Calendar c = Calendar.getInstance();
            Date date = c.getTime();
            SimpleDateFormat dateFormatter = new SimpleDateFormat(dateFormat);
            dateCurrent = dateFormatter.format(date);
        } catch (Exception e) {
            ISFLog.e(e);
        }
        return dateCurrent;
    }

    public static Date convertStringToDate(String dateString){
        Date date=null;
        try {
            Calendar c = Calendar.getInstance();
            String[] arr=dateString.split("-");
            c.set(Integer.parseInt(arr[2]),Integer.parseInt(arr[1]),Integer.parseInt(arr[0]));
            date = c.getTime();
        } catch (Exception e) {
            ISFLog.e(e);
        }
        return date;
    }

    public static String getCurrentdate(Date date) {
        String dateCurrent = "";
        try {
            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM-yyyy");
            SimpleDateFormat timeFormatter = new SimpleDateFormat("hh:mm a");
            String dateAsString = dateFormatter.format(date);
            String timeAsString = timeFormatter.format(date);
            dateCurrent = dateAsString + " at " + timeAsString;
        } catch (Exception e) {
            ISFLog.e(e);
        }
        return dateCurrent;
    }

    public static String getCurrentdate() {
        String dateCurrent = "";
        try {
            Calendar c = Calendar.getInstance();
            Date date = c.getTime();
            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
            //   SimpleDateFormat timeFormatter = new SimpleDateFormat("hh:mm a");
            String dateAsString = dateFormatter.format(date);
            //  String timeAsString = timeFormatter.format(date);
            dateCurrent = dateAsString/* + " at " + timeAsString*/;
        } catch (Exception e) {
            ISFLog.e(e);
        }
        return dateCurrent;
    }

    /**
     * get System 24 hour next date time in Date format
     */
    public static Date getNext24HourDateTime() {
        Calendar cal = Calendar.getInstance();
        // cal.setTimeZone(TimeZone.getTimeZone(Constant.TIME_ZONE));
        cal.add(Calendar.HOUR, 0);
        cal.add(Calendar.MINUTE, 0);
        cal.add(Calendar.HOUR, 24);
        // cal.add(Calendar.MINUTE, 1);
        return cal.getTime();
    }

    public static long getCurrentDateInMillis() {
        long dateCurrent = 0;
        try {
            Calendar c = Calendar.getInstance();
            Date date;
            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM-yyyy");
            String dateAsString = dateFormatter.format(c.getTime());
            date = dateFormatter.parse(dateAsString);
            dateCurrent = date.getTime();
        } catch (Exception e) {
            ISFLog.e(e);
        }
        return dateCurrent;
    }


    public static String getPreviousMonthDate() {
        long unixtime = 0;
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMddHHmm");
        dateFormatter.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));//Specify your timezone
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -30);
        try {
            unixtime = dateFormatter.parse(dateFormatter.format(cal.getTime())).getTime();//cal.getTime();
            unixtime = unixtime / 1000;
        } catch (Exception e) {
            ISFLog.e(e);
        }
        return "" + unixtime;
    }

    /*   public static String getDateDiff(String dateCreated) {
           String result;
           try {
               @SuppressLint(Constant.KEY_SIMPLE_DATE_FORMAT) SimpleDateFormat sourceFormat = new SimpleDateFormat(Constant.DATE_FORMAT_PUBLICATION);
               String dateCurrent = sourceFormat.format(new Date());//format.parse("2011-03-01 15:10:37"); // => Date is in UTC now

               @SuppressLint(Constant.KEY_SIMPLE_DATE_FORMAT) SimpleDateFormat sdf = new SimpleDateFormat(Constant.DATE_FORMAT_PUBLICATION);

               Date d1 = sdf.parse(dateCreated);
               Date d2 = sdf.parse(dateCurrent);
               long diff = d2.getTime() - d1.getTime();
               @SuppressWarnings("NumericOverflow") long diffMonths = diff / (30 * 24 * 60 * 60 * 1000);
               ISFLog.debug("month", "" + diffMonths);
               ISFLog.debug("month", "" + diffMonths);
               long diffDays = diff / (24 * 60 * 60 * 1000);
               if (diffDays == 0) {
                   long diffHours = diff / (60 * 60 * 1000) % 24;
                   if (diffHours > 0) {
                       if (diffHours == 1) {
                           result = diffHours + " hour ago";
                       } else {
                           result = diffHours + " hours ago";
                       }
                   } else {
                       long diffMinutes = diff / (60 * 1000) % 60;
                       if (diffMinutes > 0)
                           if (diffMinutes == 1) {
                               result = diffMinutes + " minute ago";
                           } else {
                               result = diffMinutes + " minutes ago";
                           }

                       else {
                           result = "Today";//"few seconds ago";
                       }
                   }
               } else if (diffDays == 1) {
                   result = diffDays + " day ago";
               } else if (1 < diffDays && diffDays < 7) {
                   result = diffDays + " days ago";
               } else if (6 < diffDays && diffDays < 30) {
                   long weekDiff = diffDays / 7;
                   if (weekDiff == 1) {
                       result = weekDiff + " week ago";
                   } else {
                       result = weekDiff + " weeks ago";
                   }
               } else if (diffDays / 30 == 1) {
                   result = (diffDays / 30) + " month ago";
               } else {
                   result = (diffDays / 30) + " months ago";
               }
           } catch (Exception e) {
               result = "";
               ISFLog.e(e);
           }
           return result;
       }
   */
    public static boolean isDateExpired(String dateCreated, String dateFormat) {
        boolean result = false;
        try {
            @SuppressLint(KEY_SIMPLE_DATE_FORMAT) SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
            if (!dateCreated.equalsIgnoreCase(sdf.format(new Date()))) {
                result = sdf.parse(sdf.format(new Date())).after(sdf.parse(dateCreated));
            }
        } catch (Exception e) {
            ISFLog.e(e);
        }
        return result;
    }


    public static int isDateToday(String dateCreated, String dateFormat) {
        int result = 1;
        try {
            @SuppressLint(KEY_SIMPLE_DATE_FORMAT) SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
            @SuppressLint(KEY_SIMPLE_DATE_FORMAT) SimpleDateFormat sdf2 = new SimpleDateFormat("dd MMM yyyy");
            result = sdf2.parse(sdf2.format(new Date())).compareTo(sdf2.parse(sdf2.format(sdf.parse(dateCreated))));
        } catch (Exception e) {
            ISFLog.e(e);
        }
        return result;
    }

    public static boolean isDateGreater(String dateFirst, String dateSecond, String dateFormat) {
        boolean result = false;
        try {
            @SuppressLint(KEY_SIMPLE_DATE_FORMAT) SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
            result = sdf.parse(dateFirst).after(sdf.parse(dateSecond));
        } catch (Exception e) {
            ISFLog.e(e);
        }
        return result;
    }

    public static void showToast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    public static void showSnackbar(View view, String text) {
        if (null != view) {
            Snackbar.make(view, text, Snackbar.LENGTH_LONG).setDuration(3000).show();
        }
    }

   /* public static void showImageWithGlide(ImageView ivUserImage, String path, Context context, int drawable) {
        try {
            Glide.with(context).load(path).diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(drawable).into(ivUserImage);
        } catch (Exception e) {
            ISFLog.e(e);
        }
    }*/


    /*  public static Spannable getSpannedText(String text, String searchedQuery, int color) {
          Spannable highlight = (Spannable) Html.fromHtml(text);
          try {
              if (Util.isNotNullOrEmpty(searchedQuery)) {
                  String[] words = searchedQuery.split(" ");
                  if (words.length > 0) {
                      String titleStr = Html.fromHtml(text).toString();
                      for (String word : words) {
                          word = " " + word.replace("(", "").replace(")", "").replace(".", "").trim() + " ";
                          Pattern pattern = Pattern.compile("(?i)" + word);
                          Matcher matcher = pattern.matcher(" " + titleStr + " ");
                          while (matcher.find()) {
                              highlight.setSpan(
                                      new ForegroundColorSpan(color),
                                      matcher.start(),
                                      matcher.end() - 2,
                                      Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                          }
                      }
                  }
              }
          } catch (Exception e) {
              ISFLog.e(e);
          }
          return highlight;
      }*/
    public static boolean isNotNullOrEmpty(String value) {
        // Null-safe, short-circuit evaluation.
        boolean result = true;
        try {
            result = !(value == null || value.equalsIgnoreCase(Constant.EMPTY));
        } catch (Exception e) {
            ISFLog.e(e);
        }
        return result;
    }
}
