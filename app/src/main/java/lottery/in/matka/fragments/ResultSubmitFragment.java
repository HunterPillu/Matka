package lottery.in.matka.fragments;


import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lottery.in.matka.R;
import lottery.in.matka.interfaces.DatabaseListener;
import lottery.in.matka.models.ChartItem;
import lottery.in.matka.utils.Constant;
import lottery.in.matka.utils.ISFLog;
import lottery.in.matka.utils.Util;

public class ResultSubmitFragment extends BaseFragment implements DatabaseListener {

    private View v;
    @Bind(R.id.tvToggleBhotnath)
    TextView tvToggleBhotnath;
    @Bind(R.id.tvToggleShakti)
    TextView tvToggleShakti;
    @Bind(R.id.tvToggleWorly)
    TextView tvToggleWorly;
    @Bind(R.id.tvDate)
    TextView tvDate;
    @Bind(R.id.tvToggleOpen)
    TextView tvToggleOpen;
    @Bind(R.id.etSingle)
    EditText etSingle;
    @Bind(R.id.etPatti)
    EditText etPatti;
    @Bind(R.id.tvToggleClose)
    TextView tvToggleClose;
    private boolean isOpenToggleActive = true;
    private String tableName;
    private String date;
    private String single;
    private String patti;
  /*  @Bind(R.id.tvToggleOpen)
    TextViewCompat tvToggleOpen;*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        activity.setTitle(Constant.TITLE_RESULT_SUBMIT);
        v = inflater.inflate(R.layout.submit_result_fragment, container, false);
        try {
            ButterKnife.bind(this, v);
            setOpenToggle();
            setGameToggle(Constant.FOR_SHAKTI);
            date = Util.getCurrentdate(Constant.DATE_FROMAT);
            tvDate.setText(date);
        } catch (Exception e) {
            ISFLog.e(e);
        }
        return v;
    }

    private void setOpenToggle() {
        if (isOpenToggleActive) {
            tvToggleOpen.setBackground(getResources().getDrawable(R.drawable.togglebackground_active_left));
            tvToggleOpen.setTextColor(Color.WHITE);
            tvToggleClose.setBackground(getResources().getDrawable(R.drawable.togglebackground_normal_right));
            tvToggleClose.setTextColor(getColor(context, R.color.black_54));
        } else {
            tvToggleOpen.setBackground(getResources().getDrawable(R.drawable.togglebackground_normal_left));
            tvToggleOpen.setTextColor(getColor(context, R.color.black_54));
            tvToggleClose.setBackground(getResources().getDrawable(R.drawable.togglebackground_active_right));
            tvToggleClose.setTextColor(Color.WHITE);
        }
    }

    private void setGameToggle(final int GAME_TYPE) {
        switch (GAME_TYPE) {
            case Constant.FOR_BHOOTHNATH:
                tvToggleBhotnath.setBackground(getResources().getDrawable(R.drawable.togglebackground_active_center));
                tvToggleBhotnath.setTextColor(Color.WHITE);
                tvToggleShakti.setBackground(getResources().getDrawable(R.drawable.togglebackground_normal_left));
                tvToggleShakti.setTextColor(getColor(context, R.color.black_54));
                tvToggleWorly.setBackground(getResources().getDrawable(R.drawable.togglebackground_normal_right));
                tvToggleWorly.setTextColor(getColor(context, R.color.black_54));
                tableName = Constant.TABLE_CHART_BHOOTNATH;
                break;
            case Constant.FOR_WORLY:
                tvToggleBhotnath.setBackground(getResources().getDrawable(R.drawable.togglebackground_normal_center));
                tvToggleBhotnath.setTextColor(getColor(context, R.color.black_54));
                tvToggleShakti.setBackground(getResources().getDrawable(R.drawable.togglebackground_normal_left));
                tvToggleShakti.setTextColor(getColor(context, R.color.black_54));
                tvToggleWorly.setBackground(getResources().getDrawable(R.drawable.togglebackground_active_right));
                tvToggleWorly.setTextColor(Color.WHITE);
                tableName = Constant.TABLE_CHART_WORLY;
                break;
            case Constant.FOR_SHAKTI:
                tvToggleBhotnath.setBackground(getResources().getDrawable(R.drawable.togglebackground_normal_center));
                tvToggleBhotnath.setTextColor(getColor(context, R.color.black_54));
                tvToggleShakti.setBackground(getResources().getDrawable(R.drawable.togglebackground_active_left));
                tvToggleShakti.setTextColor(Color.WHITE);
                tvToggleWorly.setBackground(getResources().getDrawable(R.drawable.togglebackground_normal_right));
                tvToggleWorly.setTextColor(getColor(context, R.color.black_54));
                tableName = Constant.TABLE_CHART_SHAKTI;
                break;
        }
    }

    @OnClick({R.id.tvToggleOpen, R.id.tvToggleClose, R.id.tvToggleBhotnath, R.id.tvToggleShakti, R.id.tvToggleWorly, R.id.cvDate, R.id.bSubmit})
    public void onClick(View v) {
        try {
            switch (v.getId()) {
                case R.id.tvToggleClose:
                    isOpenToggleActive = false;
                    setOpenToggle();
                    break;

                case R.id.tvToggleOpen:
                    isOpenToggleActive = true;
                    setOpenToggle();
                    break;

                case R.id.tvToggleBhotnath:
                    setGameToggle(Constant.FOR_BHOOTHNATH);
                    break;

                case R.id.tvToggleShakti:
                    setGameToggle(Constant.FOR_SHAKTI);
                    break;

                case R.id.tvToggleWorly:
                    setGameToggle(Constant.FOR_WORLY);
                    break;

                case R.id.cvDate:
                    showDatePickerDialog();
                    break;
                case R.id.bSubmit:
                    if (isValid()) {
                        activity.uploadResult(this, isOpenToggleActive, tableName, date, single, patti);
                    } else {
                        Util.showSnackbar(v, Constant.MSG_INCORRECT_PATTI_SINGLE);
                    }
                    break;
            }
        } catch (Exception e) {
            ISFLog.e(e);
        }
    }

    private boolean isValid() {
        single = etSingle.getText().toString();
        patti = etPatti.getText().toString();
        return (!TextUtils.isEmpty(single) && patti.length() == 3);
    }

    private void showDatePickerDialog() {
        try {
            Calendar cal = Calendar.getInstance();
            DatePickerDialog dialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    String mon = "" + (month + 1);
                    if (mon.length() == 1) {
                        mon = "0" + mon;
                    }
                    date = dayOfMonth + "-" + mon + "-" + year;
                    tvDate.setText(date);
                }
            }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
            dialog.getDatePicker().setMaxDate(new Date().getTime());
            //dialog.getDatePicker().setMinDate(new Date().getTime());
            dialog.show();
        } catch (Exception e) {
            ISFLog.e(e);
        }
    }

    public void onBackPressed() {
        try {
            fragmentManager.popBackStack();
        } catch (Exception e) {
            ISFLog.e(e);
        }
    }

    @Override
    public void onSuccess(String code, List<ChartItem> response) {
        ISFLog.e("h bhai data h ", "smjha ki ni ");
        hideBaseLoader();
    }

    @Override
    public void onFailure(Exception e) {
        ISFLog.e("na bhai data na h ", "smjha ki ni ");
        hideBaseLoader();
        Util.showSnackbar(v, Constant.MSG_FAILED_TO_UPDATE);
    }
}
