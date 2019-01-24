package nationalmerchantsassociation.mynetworth.view_layer.activities.debt_details;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.List;

import nationalmerchantsassociation.mynetworth.R;
import nationalmerchantsassociation.mynetworth.data_layer.models.ValueItem;
import nationalmerchantsassociation.mynetworth.utils.BaseCallback;
import nationalmerchantsassociation.mynetworth.utils.CustomDateFormatter;

import static nationalmerchantsassociation.mynetworth.utils.TextFormatterUtil.getCurrencyFormatter;

/**
 * Created by jbrannen on 9/2/17.
 */

public class RecyclerViewAdapterDebtDetails extends RecyclerView.Adapter<RecyclerViewAdapterDebtDetails.ViewHolder> {
    private List<ValueItem> mDataset;
    private BaseCallback<ValueItem> debtSelectedCallback;
    private String dateToHighlight;
    private Context context;

    public void setHighllightItemDate(String date) {
        dateToHighlight = date;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public FrameLayout debtItemLayout;
        public TextView debtDate;
        public TextView debtValue;
        public ViewHolder(View v, final BaseCallback<ValueItem> rCallback) {
            super(v);
            debtItemLayout = v.findViewById(R.id.debt_item_layout);
            debtDate = v.findViewById(R.id.debt_date);
            debtValue = v.findViewById(R.id.debt_value);
            debtItemLayout.setOnClickListener(view -> rCallback.onResponse(mDataset.get(getLayoutPosition())));
        }
    }


    public RecyclerViewAdapterDebtDetails(List<ValueItem> dataset, BaseCallback<ValueItem> assetSelected, Context context) {
        mDataset = dataset;
        dateToHighlight = "";
        this.context = context;
        this.debtSelectedCallback = assetSelected;
    }


    @Override
    public RecyclerViewAdapterDebtDetails.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.debt_detail_item, parent, false);
        return new ViewHolder(v, debtSelectedCallback);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(mDataset.get(position).getDate().equals(dateToHighlight)){
            holder.debtItemLayout.setBackgroundColor(context.getResources().getColor(R.color.colorPrimaryDark));
        }else{
            holder.debtItemLayout.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
        }
        holder.debtDate.setText(CustomDateFormatter.createDate(mDataset.get(position).getMonth(), mDataset.get(position).getYear()));
        holder.debtValue.setText("$" + getCurrencyFormatter().format(mDataset.get(position).getValue()));
    }


    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}