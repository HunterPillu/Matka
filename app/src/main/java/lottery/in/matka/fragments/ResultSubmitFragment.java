package lottery.in.matka.fragments;


import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import lottery.in.matka.R;
import lottery.in.matka.utils.ISFLog;

public class ResultSubmitFragment extends BaseFragment {

    private View v;
    private String title;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        activity.setTitle(title);
        v = inflater.inflate(R.layout.submit_result_fragment, container, false);
        try {
            ButterKnife.bind(this, v);
        } catch (Exception e) {
            ISFLog.e(e);
        }
        return v;
    }

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

    /*@OnClick({R.id.fab})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                startActivity(new Intent(activity, MainActivity.class)
                        .putExtra(Constant.INTRO_ID, Constant.UNSAVED).putExtra(Constant.IS_EDITABLE, true));
                break;
        }
    }*/

    public void onBackPressed() {
        try {
            fragmentManager.popBackStack();
        } catch (Exception e) {
            ISFLog.e(e);
        }
    }
}
