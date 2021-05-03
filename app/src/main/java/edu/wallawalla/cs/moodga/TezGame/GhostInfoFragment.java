package edu.wallawalla.cs.moodga.TezGame;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GhostInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GhostInfoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private Integer mParam1;
    private String mParam2;

    public GhostInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GhostInfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GhostInfoFragment newInstance(String param1, String param2) {
        GhostInfoFragment fragment = new GhostInfoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt("classType");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ghost_info, container, false);
        TextView ghostinfoTV = view.findViewById(R.id.ghostinfoTV);
        switch(mParam1) {
            case 1:
                ghostinfoTV.setText("This Ghost is fast and deals extra damage to enemies.");
                break;
            case 2:
                ghostinfoTV.setText("This Ghost is slow but has a lot of health. Enemies tend to ignore this ghost more.");
                break;
            case 3:
                ghostinfoTV.setText("This Ghost does not have much health but can be invisible. This Ghost deals short, quick bursts of damage.");
                break;
        }
        return view;
    }
}