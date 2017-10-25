
package lottery.in.matka.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import lottery.in.matka.R;
import lottery.in.matka.models.ChartItem;
import lottery.in.matka.utils.ISFLog;


public class ChartAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<ChartItem> list;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_DATE = 2;

    public void setTotalWeekDays(int totalWeekDays) {
        this.totalWeekDays = totalWeekDays;
    }

    private int totalWeekDays;


    public ChartAdapter(Context context, List<ChartItem> list) {
        this.context = context;
        this.list = list;
    }

    //    need to override this method
    @Override
    public int getItemViewType(int position) {
        return (position % totalWeekDays == 0) ? TYPE_DATE : TYPE_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        if (viewType == TYPE_ITEM) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.chart_item, parent, false);
            return new TitleHolder(v);
        } else {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.chart_date, parent, false);
            return new DateHolder(v);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder hld, final int position) {
        try {
            final ChartItem vo = list.get(position);
            if (hld instanceof TitleHolder) {
                final TitleHolder holder = (TitleHolder) hld;
                holder.tvPattiOpen.setText(vo.getPattiOpen());
                holder.tvSingleOpen.setText(vo.getSingleOpen());
                holder.tvSingleClose.setText(vo.getSingleClose());
                holder.tvPattiClose.setText(vo.getPattiClose());
                // holder.tvDate.setText(vo.getDate());
                //  holder.tvDate.setVisibility(vo.isFirstDayOfWeek() ? View.VISIBLE : View.GONE);
                if (vo.isDoubling()) {
                    holder.tvPattiOpen.setTextColor(Color.RED);
                    holder.tvSingleOpen.setTextColor(Color.RED);
                    holder.tvSingleClose.setTextColor(Color.RED);
                    holder.tvPattiClose.setTextColor(Color.RED);
                } else {
                    holder.tvPattiOpen.setTextColor(Color.BLACK);
                    holder.tvSingleOpen.setTextColor(Color.BLACK);
                    holder.tvSingleClose.setTextColor(Color.BLACK);
                    holder.tvPattiClose.setTextColor(Color.BLACK);
                }
            }

        } catch (Exception e) {
            ISFLog.e(e);
        }
    }

    @Override
    public int getItemCount() {
        try {
            return list.size();
        } catch (Exception e) {
            return 0;
        }
    }

    public static class TitleHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tvPattiOpen)
        TextView tvPattiOpen;
        @Bind(R.id.tvPattiClose)
        TextView tvPattiClose;
        @Bind(R.id.tvSingleOpen)
        TextView tvSingleOpen;
        @Bind(R.id.tvSingleClose)
        TextView tvSingleClose;
        /*@Bind(R.id.tvDate)
        TextView tvDate;*/

/*
        @Bind(R.id.cvMain)
        CardView cvMain;*/

        public TitleHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static class DateHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tvDate)
        TextView tvDate;


        public DateHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

