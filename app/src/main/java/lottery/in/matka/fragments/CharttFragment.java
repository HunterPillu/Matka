package lottery.in.matka.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import butterknife.Bind;
import butterknife.ButterKnife;
import lottery.in.matka.R;
import lottery.in.matka.models.ChartItem;
import lottery.in.matka.utils.ISFLog;

public class CharttFragment extends BaseFragment {


    @Bind(R.id.recyclerView)
    RecyclerView mMessageRecyclerView;

    private LinearLayoutManager mLinearLayoutManager;
    private FirebaseRecyclerAdapter<ChartItem, MessageViewHolder> mFirebaseAdapter;
    private DatabaseReference mFirebaseDatabaseReference;

    public static final String CHART_SHAKTI = "chart_shakti";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        //  activity.selectedFragment = this;
        //  activity.setTheme(R.style.ThemeOrange);
        View v = inflater.inflate(R.layout.fragment_chart, container, false);
        try {
            ButterKnife.bind(this, v);
            mLinearLayoutManager = new LinearLayoutManager(context);
            mLinearLayoutManager.setStackFromEnd(true);
            mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
            initRecycle();
            mFirebaseDatabaseReference.child(CHART_SHAKTI).push().setValue(new ChartItem());

        } catch (Exception e) {
            ISFLog.e(e);
        }
        return v;
    }

   /* @OnClick(R.id.sendButton)
    public void OnSendButtonClicked() {
        String msg = mMessageEditText.getText().toString();
        if (msg.equals(Constant.EMPTY)) {
            startAnimation(mSendButton, 0, 400);
        } else {
            FriendlyMessage friendlyMessage = new FriendlyMessage(playerId, mMessageEditText.getText().toString(), activity.userName,
                    activity.userImageUrl);
            mFirebaseDatabaseReference.child(MESSAGES_CHILD).push().setValue(friendlyMessage);
            mMessageEditText.setText("");
        }
    }*/

    @Override
    public void onStop() {
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        super.onStop();
    }

    private void initRecycle() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<ChartItem, MessageViewHolder>(
                ChartItem.class,
                R.layout.chart_item,
                MessageViewHolder.class,
                mFirebaseDatabaseReference.child(CHART_SHAKTI)) {

            @Override
            protected ChartItem parseSnapshot(DataSnapshot snapshot) {
                ChartItem friendlyMessage = super.parseSnapshot(snapshot);
                /*if (friendlyMessage != null) {
                    friendlyMessage.setId(snapshot.getKey());
                }*/
                ISFLog.e("json", "" + new Gson().toJson(friendlyMessage));
                return friendlyMessage;
            }

            @Override
            protected void populateViewHolder(final MessageViewHolder holder,
                                              ChartItem vo, int position) {
                try {
                    holder.tvPattiOpen.setText(vo.getPattiOpen());
                    holder.tvSingleOpen.setText(vo.getSingleOpen());
                    holder.tvSingleClose.setText(vo.getSingleClose());
                    holder.tvPattiClose.setText(vo.getPattiClose());
                    if (vo.isDoubling()) {
                        holder.tvPattiOpen.setTextColor(Color.RED);
                        holder.tvSingleOpen.setTextColor(Color.RED);
                        holder.tvSingleClose.setTextColor(Color.RED);
                        holder.tvPattiClose.setTextColor(Color.RED);
                    }
                } catch (Exception e) {
                    ISFLog.e(e);
                }
            }
        };

        mFirebaseAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                int friendlyMessageCount = mFirebaseAdapter.getItemCount();
                int lastVisiblePosition = mLinearLayoutManager.findLastCompletelyVisibleItemPosition();
                // If the recycler view is initially being loaded or the user is at the bottom of the list, scroll
                // to the bottom of the list to show the newly added message.
                if (lastVisiblePosition == -1 ||
                        (positionStart >= (friendlyMessageCount - 1) && lastVisiblePosition == (positionStart - 1))) {
                    mMessageRecyclerView.scrollToPosition(positionStart);
                }
            }
        });

        mMessageRecyclerView.setLayoutManager(mLinearLayoutManager);
        mMessageRecyclerView.setAdapter(mFirebaseAdapter);
    }


    public void onBackPressed() {
        try {
            fragmentManager.popBackStack();
        } catch (Exception e) {
            ISFLog.e(e);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //  activity.selectedFragment = this;
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


   /* public void showBaseLoader() {
        showLoader(context);
    }*/

    public static class MessageViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tvPattiOpen)
        TextView tvPattiOpen;
        @Bind(R.id.tvPattiClose)
        TextView tvPattiClose;
        @Bind(R.id.tvSingleOpen)
        TextView tvSingleOpen;
        @Bind(R.id.tvSingleClose)
        TextView tvSingleClose;
/*
        @Bind(R.id.cvMain)
        CardView cvMain;*/

        public MessageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}


