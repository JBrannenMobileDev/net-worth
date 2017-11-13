package nationalmerchantsassociation.mynetworth.view_layer.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

import nationalmerchantsassociation.mynetworth.R;
import nationalmerchantsassociation.mynetworth.data_layer.models.Asset;
import nationalmerchantsassociation.mynetworth.utils.BaseCallback;

/**
 * Created by jbrannen on 9/2/17.
 */

public class RecyclerViewAdapterAssets extends RecyclerView.Adapter<RecyclerViewAdapterAssets.ViewHolder> {
    private List<Asset> mDataset;
    private BaseCallback<Asset> assetSelectedCallback;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public FrameLayout assetItemLayout;
        public TextView assetName;
        public TextView assetValue;
        public TextView category;
        public ViewHolder(View v, final BaseCallback<Asset> rCallback) {
            super(v);
            assetItemLayout = v.findViewById(R.id.asset_item_layout);
            assetName = v.findViewById(R.id.asset_name);
            assetValue = v.findViewById(R.id.asset_value);
            category = v.findViewById(R.id.category_text_view);
            assetItemLayout.setOnClickListener(view -> rCallback.onResponse(mDataset.get(getLayoutPosition())));
        }
    }

    public RecyclerViewAdapterAssets(List<Asset> dataset, BaseCallback<Asset> assetSelected) {
        mDataset = dataset;
        this.assetSelectedCallback = assetSelected;
    }

    @Override
    public RecyclerViewAdapterAssets.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.asset_item, parent, false);


        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v, assetSelectedCallback);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        holder.assetName.setText(mDataset.get(position).getName());
        holder.assetValue.setText("$" + formatter.format(mDataset.get(position).getMarketValue()));
        holder.category.setText(mDataset.get(position).getCategory());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}