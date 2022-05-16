package com.example.habitimia.ui.arena;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.habitimia.R;
import com.example.habitimia.data.model.User;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RankingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RankingFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView Number;
    private TextView Username;
    private TextView Battles;
    private TextView Rate;
    private Button Duel;

    private User user;
    private int number;

    public static RankingFragment newInstance(User user, int number) {

        RankingFragment frag = new RankingFragment();
        frag.user = user;
        frag.number = number;

        return frag;
    }

    public RankingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RankingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RankingFragment newInstance(String param1, String param2) {
        RankingFragment fragment = new RankingFragment();
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
        View view = inflater.inflate(R.layout.fragment_ranking, container, false);
        Number = (TextView) view.findViewById(R.id.number_ranking);
        Username = (TextView) view.findViewById(R.id.username_ranking);
        Battles = (TextView) view.findViewById(R.id.battles_ranking);
        Rate = (TextView) view.findViewById(R.id.rate_ranking);
        Duel = (Button) view.findViewById(R.id.duel_btn);

        if(user != null) {
            Number.setText(number + ".");
            Username.setText(user.getUsername());

            Duel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO : DUEL
                    System.out.println(user.getUsername());
                }
            });

        }

        return view;
    }
}