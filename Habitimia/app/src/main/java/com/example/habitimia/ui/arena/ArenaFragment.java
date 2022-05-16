package com.example.habitimia.ui.arena;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.habitimia.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ArenaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ArenaFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private LinearLayout Ranking;

    public ArenaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ArenaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ArenaFragment newInstance(String param1, String param2) {
        ArenaFragment fragment = new ArenaFragment();
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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_arena, container, false);
        Ranking = (LinearLayout) view.findViewById(R.id.ranking_content);
        View child1 = getLayoutInflater().inflate(R.layout.fragment_ranking, null);
        View child2 = getLayoutInflater().inflate(R.layout.fragment_ranking, null);
        Ranking.addView(child1);
        Ranking.addView(child2);
        return view;
    }
}