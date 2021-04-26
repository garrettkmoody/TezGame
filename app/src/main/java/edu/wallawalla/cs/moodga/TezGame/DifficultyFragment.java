package edu.wallawalla.cs.moodga.TezGame;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DifficultyFragment extends DialogFragment {

    public interface OnDifficultySelectedListener {
        void onDifficultyClick(int which);
    }

    private OnDifficultySelectedListener mListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setTitle("Change Difficulty");
        builder.setItems(R.array.difficulties, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                mListener.onDifficultyClick(which);
            }
        });
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (OnDifficultySelectedListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}