package com.example.habitimia.ui.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.habitimia.R;
import com.example.habitimia.ui.MainActivity;
import com.example.habitimia.ui.arena.ArenaFragment;
import com.example.habitimia.ui.quest.DailyFragment;
import com.example.habitimia.ui.guild.GuildFragment;
import com.example.habitimia.ui.quest.QuestFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private ImageButton ArenaBtn;
    private ImageButton DailyBtn;
    private ImageButton GuildBtn;
    private ImageButton QuestBtn;

    private TextView PlayerName;
    private TextView PlayerLevel;
    private TextView PlayerLife;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ArenaBtn = (ImageButton) view.findViewById(R.id.home_arena_btn);
        DailyBtn = (ImageButton) view.findViewById(R.id.home_dailies_btn);
        GuildBtn = (ImageButton) view.findViewById(R.id.home_guild_btn);
        QuestBtn = (ImageButton) view.findViewById(R.id.home_quests_btn);

        PlayerName = (TextView) view.findViewById(R.id.PlayerName_home);
        PlayerLevel = (TextView) view.findViewById(R.id.PlayerLevel);
        PlayerLife = (TextView) view.findViewById(R.id.PlayerLife);

        PlayerName.setText(((MainActivity) getActivity()).user.getUsername());
        //PlayerLevel.setText(((MainActivity) getActivity()).user.getStatistics().get);
        PlayerLife.setText("" + ((MainActivity) getActivity()).user.getStatistics().getHP());

        ArenaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).LoadFragment(new ArenaFragment());
            }
        });

        DailyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).LoadFragment(new DailyFragment());
            }
        });

        GuildBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).LoadFragment(new GuildFragment());
            }
        });

        QuestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).LoadFragment(new QuestFragment());
            }
        });

        ((MainActivity) getActivity()).setBackgroundColor(getResources().getColor(R.color.teal_200));
        ((MainActivity) getActivity()).UpdateFABIcon(R.drawable.logout);

        // Inflate the layout for this fragment
        return view;

    }
}