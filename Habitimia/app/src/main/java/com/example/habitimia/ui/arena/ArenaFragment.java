package com.example.habitimia.ui.arena;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.habitimia.R;
import com.example.habitimia.data.model.User;
import com.example.habitimia.ui.MainActivity;
import com.example.habitimia.ui.guild.GuildFragment;
import com.example.habitimia.ui.home.HomeFragment;
import com.example.habitimia.util.Server;

import java.util.ArrayList;
import java.util.List;

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
    private List<User> users;

    private ImageButton Back;

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
        Back = (ImageButton) view.findViewById(R.id.back_arena);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).LoadFragment(new HomeFragment());
            }
        });

        users = new ArrayList<User>();
        User u = new User();
        u.setUsername("Theodore Mia");
        users.add(u);
        User u1 = new User();
        u1.setUsername("Ian Burton");
        users.add(u1);
        User u2 = new User();
        u2.setUsername("Roxy Hansen");
        users.add(u2);
        users.add(u1);
        users.add(u2);
        users.add(u1);
        users.add(u2);

        users = Server.getUsers();
        users.add(u1);
        users.add(u2);
        FragmentManager fragMan = getFragmentManager();
        FragmentTransaction fragTransaction = fragMan.beginTransaction();

        for(int fragCount=0; fragCount < users.size(); fragCount++) {
            RankingFragment ranking = RankingFragment.newInstance(users.get(fragCount), fragCount+1);

            fragTransaction.add(Ranking.getId(), ranking , "fragment" + fragCount);

        }

        fragTransaction.commit();

        ((MainActivity) getActivity()).setBackgroundColor(getResources().getColor(R.color.white));
        ((MainActivity) getActivity()).UpdateFABIcon(R.drawable.arena_image);
        return view;
    }
}