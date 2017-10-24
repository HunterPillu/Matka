
package lottery.in.matka.adapters;

import android.app.ProgressDialog;
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


public class ChartAdapter extends RecyclerView.Adapter<ChartAdapter.TitleHolder> {
    private Context context;
    private List<ChartItem> list;
    private ProgressDialog progressDialog;


    public ChartAdapter(Context context, List<ChartItem> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public TitleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chart_item, parent, false);
        return new TitleHolder(view);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onBindViewHolder(final TitleHolder holder, final int position) {
        try {
            final ChartItem vo = list.get(position);
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
           /* Glide.with(context).load(vo.getMediaUrl()).diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.image_placeholder).into(holder.ivPhoto);*/

          /*  holder.cvMain.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    ISFLog.e("onLongPress", "onLongPress");
                    holder.rlDelete.setVisibility(View.VISIBLE);
                    return true;
                }
            });*/

        } catch (Exception e) {
            ISFLog.e(e);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
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
/*
        @Bind(R.id.cvMain)
        CardView cvMain;*/

        public TitleHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

