package nationalmerchantsassociation.mynetworth.view_layer.activities.asset_details;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.List;

import io.realm.RealmResults;
import nationalmerchantsassociation.mynetworth.R;
import nationalmerchantsassociation.mynetworth.data_layer.models.Asset;
import nationalmerchantsassociation.mynetworth.data_layer.models.ValueItem;
import nationalmerchantsassociation.mynetworth.utils.BaseCallback;
import nationalmerchantsassociation.mynetworth.utils.CustomDateFormatter;

import static nationalmerchantsassociation.mynetworth.utils.TextFormatterUtil.getCurrencyFormatter;

/**
 * Created by jbrannen on 9/2/17.
 */

public class RecyclerViewAdapterAssetDetails extends RecyclerView.Adapter<RecyclerViewAdapterAssetDetails.ViewHolder> {
    private List<ValueItem> mDataset;
    private BaseCallback<ValueItem> assetSelectedCallback;
    private String dateToHighlight;
    private Context context;

    public void setHighllightItemDate(String date) {
        dateToHighlight = date;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public FrameLayout assetItemLayout;
        public TextView assetDate;
        public TextView assetValue;
        public ViewHolder(View v, final BaseCallback<ValueItem> rCallback) {
            super(v);
            assetItemLayout = v.findViewById(R.id.asset_item_layout);
            assetDate = v.findViewById(R.id.asset_date);
            assetValue = v.findViewById(R.id.asset_value);
            assetItemLayout.setOnClickListener(view -> rCallback.onResponse(mDataset.get(getLayoutPosition())));
        }
    }


    public RecyclerViewAdapterAssetDetails(List<ValueItem> dataset, BaseCallback<ValueItem> assetSelected, Context context) {
        mDataset = dataset;
        dateToHighlight = "";
        this.context = context;
        this.assetSelectedCallback = assetSelected;
    }


    @Override
    public RecyclerViewAdapterAssetDetails.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.asset_detail_item, parent, false);
        return new ViewHolder(v, assetSelectedCallback);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(mDataset.get(position).getDate().equals(dateToHighlight)){
            holder.assetItemLayout.setBackgroundColor(context.getResources().getColor(R.color.colorPrimaryDark));
        }else{
            holder.assetItemLayout.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
        }
        holder.assetDate.setText(CustomDateFormatter.createDate(mDataset.get(position).getMonth(), mDataset.get(position).getYear()));
        holder.assetValue.setText("$" + getCurrencyFormatter().format(mDataset.get(position).getValue()));
    }


    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}