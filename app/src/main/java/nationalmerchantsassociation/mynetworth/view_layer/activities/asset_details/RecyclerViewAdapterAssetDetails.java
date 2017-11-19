package nationalmerchantsassociation.mynetworth.view_layer.activities.asset_details;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.List;

import nationalmerchantsassociation.mynetworth.R;
import nationalmerchantsassociation.mynetworth.data_layer.models.Asset;
import nationalmerchantsassociation.mynetworth.utils.BaseCallback;

import static nationalmerchantsassociation.mynetworth.utils.TextFormatterUtil.getCurrencyFormatter;

/**
 * Created by jbrannen on 9/2/17.
 */

public class RecyclerViewAdapterAssetDetails extends RecyclerView.Adapter<RecyclerViewAdapterAssetDetails.ViewHolder> {
    private List<Asset> mDataset;
    private BaseCallback<Asset> assetSelectedCallback;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public FrameLayout assetItemLayout;
        public TextView assetDate;
        public TextView assetValue;
        public ViewHolder(View v, final BaseCallback<Asset> rCallback) {
            super(v);
            assetItemLayout = v.findViewById(R.id.asset_item_layout);
            assetDate = v.findViewById(R.id.asset_date);
            assetValue = v.findViewById(R.id.asset_value);
            assetItemLayout.setOnClickListener(view -> rCallback.onResponse(mDataset.get(getLayoutPosition())));
        }
    }

    public RecyclerViewAdapterAssetDetails(List<Asset> dataset, BaseCallback<Asset> assetSelected) {
        mDataset = dataset;
        this.assetSelectedCallback = assetSelected;
    }

    @Override
    public RecyclerViewAdapterAssetDetails.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.asset_detail_item, parent, false);


        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v, assetSelectedCallback);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.assetDate.setText(mDataset.get(position).getCurrentValueItem().getMonth() + " " + mDataset.get(position).getCurrentValueItem().getYear());
        holder.assetValue.setText("$" + getCurrencyFormatter().format(mDataset.get(position).getCurrentValueItem().getValue()));
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}