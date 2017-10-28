package lottery.in.matka.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import lottery.in.matka.R;
import lottery.in.matka.interfaces.DatabaseListener;
import lottery.in.matka.models.ChartItem;
import lottery.in.matka.utils.Constant;
import lottery.in.matka.utils.ISFLog;
import lottery.in.matka.utils.Util;

public class DashboardFragment extends BaseFragment implements DatabaseListener {


    private View v;
    private long backPressed;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        getActivity().setTitle(Constant.TITLE_DASHBOARD);
        v = inflater.inflate(R.layout.fragment_dashboard, container, false);
        try {
            ButterKnife.bind(this, v);


        } catch (Exception e) {
            ISFLog.e(e);
        }
        return v;
    }


    @OnClick({R.id.cvGameBN, R.id.cvGameWorly, R.id.cvGameShakti, R.id.tvAdmin})
    public void onClick(View v) {
        showBaseLoader(Constant.MSG_PLEASE_WAIT);
        switch (v.getId()) {
            case R.id.cvGameBN:
                //  gotoChartFragment(this, Constant.TABLE_CHART_BHOOTNATH, Constant.TITLE_BHOOTNATH);
                activity.fetchChart(this, Constant.TABLE_CHART_BHOOTNATH);
                //  activity.fetchChart(this, Constant.TABLE_CHART_SHAKTI);
                break;
            case R.id.cvGameWorly:
                //  gotoChartFragment(this, Constant.TABLE_CHART_WORLY, Constant.TITLE_WORLY);
                activity.fetchChart(this, Constant.TABLE_CHART_WORLY);
                //activity.fetchChart(this, Constant.TABLE_CHART_SHAKTI);
                break;
            case R.id.cvGameShakti:
                // gotoChartFragment(this, Constant.TABLE_CHART_SHAKTI, Constant.TITLE_SHAKTI);
                activity.fetchChart(this, Constant.TABLE_CHART_SHAKTI);
                break;
            case R.id.tvAdmin:
                hideBaseLoader();
                new LoginFragment().show(fragmentManager, "login");
                break;
        }
    }

    public void onBackPressed() {
        try {
            if (backPressed + 2000 > System.currentTimeMillis()) {
                activity.finish();
            } else {
                Util.showToast(context, Constant.APP_EXIT_TOAST_MSG);
            }
            backPressed = System.currentTimeMillis();

        } catch (Exception e) {
            ISFLog.e(e);
        }
    }

 /*   public void dialogLogin() {
        try {

            if (null != progressDialog && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            progressDialog = new ProgressDialog(context);
            progressDialog = ProgressDialog.show(context, "", "", true);
            progressDialog.setCanceledOnTouchOutside(true);
            progressDialog.setCancelable(true);
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            progressDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
            progressDialog.setContentView(R.layout.dialog_login);

            AppCompatButton bLogin = (AppCompatButton) progressDialog.findViewById(R.id.bLogin);
            final EditText etPassword = (EditText) progressDialog.findViewById(R.id.etPassword);
            bLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    progressDialog.dismiss();
                    String password = etPassword.getText().toString();
                    if (TextUtils.isEmpty(password)) {
                        etPassword.setError(Constant.MSG_ENTER_PASSWORD);
                    } else {
                        checkLogin(password);
                    }

                }
            });
        } catch (Exception e) {
            ISFLog.e(e);
        }
    }*/


    @Override
    public void onSuccess(String tableName, List<ChartItem> response) {
        hideBaseLoader();
        gotoChartFragment(this, tableName, response);
    }

    @Override
    public void onFailure(Exception e) {
        hideBaseLoader();
        ISFLog.e(e);
    }
}
