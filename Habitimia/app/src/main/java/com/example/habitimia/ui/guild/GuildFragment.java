package com.example.habitimia.ui.guild;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.habitimia.R;
import com.example.habitimia.data.adapter.QuestAdapter;
import com.example.habitimia.data.model.Quest;
import com.example.habitimia.ui.MainActivity;
import com.example.habitimia.ui.home.HomeFragment;
import com.example.habitimia.util.RecyclerItemTouchHelper;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GuildFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GuildFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ImageButton Back;
    private TextView Name;
    private TextView Members;

    private RecyclerView GuildRecyclerView;
    private QuestAdapter adapter;

    private List<Quest> questList;

    public GuildFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GuildFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GuildFragment newInstance(String param1, String param2) {
        GuildFragment fragment = new GuildFragment();
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
        View view = inflater.inflate(R.layout.fragment_guild, container, false);
        Back = (ImageButton) view.findViewById(R.id.back_guild);
        Name = (TextView) view.findViewById(R.id.guildName);
        Members = (TextView) view.findViewById(R.id.guildMembers);

        Name.setText(((MainActivity) getActivity()).user.getGuild().getName());
        Members.setText("" + ((MainActivity) getActivity()).user.getGuild().getMembers().size());

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.framecontainer, new HomeFragment()).commit();
            }
        });
        ((MainActivity) getActivity()).setBackgroundColor(getResources().getColor(R.color.white));

        questList = new ArrayList<>();
        GuildRecyclerView = view.findViewById(R.id.GuildRecyclerView);
        GuildRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new QuestAdapter(((MainActivity) getActivity()));
        GuildRecyclerView.setAdapter(adapter);

        ItemTouchHelper itemTouchHelper = new
                ItemTouchHelper(new RecyclerItemTouchHelper(adapter));
        itemTouchHelper.attachToRecyclerView(GuildRecyclerView);

        Quest dummy_quest = new Quest();
        dummy_quest.setId(1l);
        dummy_quest.setName("Dummy Quest");

        questList.add(dummy_quest);
        questList.add(dummy_quest);
        questList.add(dummy_quest);
        questList.add(dummy_quest);
        questList.add(dummy_quest);
        questList.add(dummy_quest);
        questList.add(dummy_quest);
        questList.add(dummy_quest);
        questList.add(dummy_quest);

//        questList = Server.getQuests(((MainActivity) getActivity()).user);

        adapter.setTasks(questList);

        return view;
    }
}