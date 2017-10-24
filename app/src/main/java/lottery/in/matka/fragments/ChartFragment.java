package lottery.in.matka.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import lottery.in.matka.R;
import lottery.in.matka.adapters.ChartAdapter;
import lottery.in.matka.models.ChartItem;
import lottery.in.matka.utils.Constant;
import lottery.in.matka.utils.ISFLog;

public class ChartFragment extends BaseFragment {

    private View v;
    private long backPressed;
    @Bind(R.id.recyclerView)
    public RecyclerView recyclerView;
    private ChartAdapter adapter;
    private List<ChartItem> chartItemList;
    private int SHOW_CHART_FOR;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        getActivity().setTitle(Constant.TITLE_DASHBOARD);
        v = inflater.inflate(R.layout.fragment_chart, container, false);
        try {
            ButterKnife.bind(this, v);

            initRecycleview();
        } catch (Exception e) {
            ISFLog.e(e);
        }
        return v;
    }

    private void initRecycleview() {
        try {
            chartItemList = new ArrayList<>();
            for (int i = 0; i < 153; i++) {
                chartItemList.add(new ChartItem());
            }
            recyclerView.setHasFixedSize(true);
            ISFLog.d("json", "" + new Gson().toJson(chartItemList));
            StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(6, StaggeredGridLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);
            adapter = new ChartAdapter(context, chartItemList);
            recyclerView.setAdapter(adapter);
            ISFLog.e("itemcount", "" + recyclerView.getAdapter().getItemCount());
            recyclerView.scrollToPosition(adapter.getItemCount() - 1);

        } catch (Exception e) {
            ISFLog.e(e);
        }
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


    public static Fragment newInstance(int showChartFor) {
        ChartFragment fragment = new ChartFragment();
        fragment.SHOW_CHART_FOR = showChartFor;
        return fragment;
    }
}
