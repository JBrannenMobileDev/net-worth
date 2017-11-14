package nationalmerchantsassociation.mynetworth.view_layer.dialog_fragments;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import nationalmerchantsassociation.mynetworth.R;
import nationalmerchantsassociation.mynetworth.view_layer.activities.create_asset.CreateAssetActivity;
import nationalmerchantsassociation.mynetworth.view_layer.activities.create_debt.CreateDebtActivity;


/**
 * Created by jbrannen on 6/14/17.
 */

public class AddItemAlertDialog extends DialogFragment {

    public AddItemAlertDialog(){

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Select a category").
        setItems(R.array.category_names, (dialog, which) -> {
            Intent intent = null;
            switch(which){
                case 0://asset
                    intent = new Intent(getActivity(), CreateAssetActivity.class);
                    break;
                case 1://debt
                    intent = new Intent(getActivity(), CreateDebtActivity.class);
                    break;
            }
            if(intent != null)
                getActivity().startActivity(intent);
            dismiss();
        });
        return builder.create();
    }
}
