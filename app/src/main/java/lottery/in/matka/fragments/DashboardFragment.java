package lottery.in.matka.fragments;


import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import lottery.in.matka.R;
import lottery.in.matka.utils.Constant;
import lottery.in.matka.utils.ISFLog;
import lottery.in.matka.utils.Util;

public class DashboardFragment extends BaseFragment {


    private View v;
    private long backPressed;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        getActivity().setTitle(Constant.TITLE_DASHBOARD);
        v = inflater.inflate(R.layout.fragment_dashboard, container, false);
        try {
            ButterKnife.bind(this, v);

            //initRecycleview();
        } catch (Exception e) {
            ISFLog.e(e);
        }
        return v;
    }

    /*private void initRecycleview() {
        try {
            recyclerView.setHasFixedSize(true);
            LinearLayoutManager layoutManager = new LinearLayoutManager(context);
            recyclerView.setLayoutManager(layoutManager);
            adapter = new ContactDetailsAdapter(context, fragmentManager, distList);
            recyclerView.setAdapter(adapter);

        } catch (Exception e) {
            ISFLog.e(e);
        }
    }*/

    @Override
    public void onResume() {
        super.onResume();
        //activity.selectedFragment = this;
        try {
            if (getView() != null) {
                getView().setFocusableInTouchMode(true);
                getView().requestFocus();
                getView().setOnKeyListener(new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View v, int keyCode, KeyEvent event) {
                        if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_BACK) {
                            onBackPressed();
                        }
                        return true;
                    }
                });
            }
        } catch (Exception e) {
            ISFLog.e(e);
        }
    }

    @OnClick({R.id.cvGameBN, R.id.cvGameWorly, R.id.cvGameShakti})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cvGameBN:
                gotoChartFragment(this, Constant.FOR_BHOOTHNATH);
                break;
            case R.id.cvGameWorly:
                gotoChartFragment(this, Constant.FOR_WORLY);
                break;
            case R.id.cvGameShakti:
                gotoChartFragment(this, Constant.FOR_SHAKTI);
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
}
