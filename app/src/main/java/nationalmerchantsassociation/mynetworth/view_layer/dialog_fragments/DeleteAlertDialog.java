package nationalmerchantsassociation.mynetworth.view_layer.dialog_fragments;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by jbrannen on 6/14/17.
 */

public class DeleteAlertDialog extends DialogFragment {

    private YesSelected mListener;
    private String assetName;

    public DeleteAlertDialog(){}

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (YesSelected) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if(getArguments().getString("name") != null){
            assetName = getArguments().getString("name");
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Are you sure?");
        builder.setPositiveButton("Yes", (dialog, id) -> {
            mListener.onDelete(assetName);
            dismiss();
        });
        builder.setNegativeButton("Cancel", (dialogInterface, i) -> dismiss());
        return builder.create();
    }

    public interface YesSelected {
        void onDelete(String assetName);
    }
}
