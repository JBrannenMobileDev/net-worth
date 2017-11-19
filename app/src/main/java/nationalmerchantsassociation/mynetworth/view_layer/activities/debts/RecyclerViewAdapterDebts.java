package nationalmerchantsassociation.mynetworth.view_layer.activities.debts;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

import nationalmerchantsassociation.mynetworth.R;
import nationalmerchantsassociation.mynetworth.data_layer.models.Debt;
import nationalmerchantsassociation.mynetworth.utils.BaseCallback;

/**
 * Created by jbrannen on 9/2/17.
 */

public class RecyclerViewAdapterDebts extends RecyclerView.Adapter<RecyclerViewAdapterDebts.ViewHolder> {
    private List<Debt> mDataset;
    private BaseCallback<Debt> debtSelectedCallback;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public FrameLayout debtItemLayout;
        public TextView debtName;
        public TextView debtValue;
        public TextView category;
        public ViewHolder(View v, final BaseCallback<Debt> rCallback) {
            super(v);
            debtItemLayout = v.findViewById(R.id.debt_item_layout);
            debtName = v.findViewById(R.id.debt_name);
            debtValue = v.findViewById(R.id.debt_value);
            category = v.findViewById(R.id.category_text_view);
            debtItemLayout.setOnClickListener(view -> rCallback.onResponse(mDataset.get(getLayoutPosition())));
        }
    }

    public RecyclerViewAdapterDebts(List<Debt> dataset, BaseCallback<Debt> debtSelected) {
        mDataset = dataset;
        this.debtSelectedCallback = debtSelected;
    }

    @Override
    public RecyclerViewAdapterDebts.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.debt_item, parent, false);


        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v, debtSelectedCallback);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        holder.debtName.setText(mDataset.get(position).getName());
        holder.debtValue.setText("$" + formatter.format(mDataset.get(position).getCurrentValueItem().getValue()));
        holder.category.setText(mDataset.get(position).getCategory());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}