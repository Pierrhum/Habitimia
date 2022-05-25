package com.example.habitimia.ui.quest;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.habitimia.R;
import com.example.habitimia.data.adapter.QuestAdapter;
import com.example.habitimia.data.model.AdventurerClass;
import com.example.habitimia.data.model.OwnerType;
import com.example.habitimia.data.model.Quest;
import com.example.habitimia.data.model.User;
import com.example.habitimia.ui.MainActivity;
import com.example.habitimia.ui.home.HomeFragment;
import com.example.habitimia.util.DialogCloseListener;
import com.example.habitimia.util.RecyclerItemTouchHelper;
import com.example.habitimia.util.Server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuestFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuestFragment extends Fragment implements DialogCloseListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private User user;
    private ImageButton Back;

    private RecyclerView tasksRecyclerView;
    private QuestAdapter adapter;

    private List<Quest> questList;

    public QuestFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QuestFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QuestFragment newInstance(String param1, String param2) {
        QuestFragment fragment = new QuestFragment();
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

        View view = inflater.inflate(R.layout.fragment_quest, container, false);

        Back = (ImageButton) view.findViewById(R.id.back_quest);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.framecontainer, new HomeFragment()).commit();
            }
        });
        ((MainActivity) getActivity()).setBackgroundColor(getResources().getColor(R.color.white));
        ((MainActivity) getActivity()).UpdateFABIcon(R.drawable.ic_add);

        questList = new ArrayList<>();
        tasksRecyclerView = view.findViewById(R.id.tasksRecyclerView);
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new QuestAdapter(((MainActivity) getActivity()));
        tasksRecyclerView.setAdapter(adapter);

        ItemTouchHelper itemTouchHelper = new
                ItemTouchHelper(new RecyclerItemTouchHelper(adapter));
        itemTouchHelper.attachToRecyclerView(tasksRecyclerView);

        Quest dummy_quest = new Quest();
        dummy_quest.setId(1l);
        dummy_quest.setName("Dummy Quest Name");
        dummy_quest.setDetails("Dummy Quest Details");
        dummy_quest.setDifficulty(AdventurerClass.B);

        questList.add(dummy_quest);
        questList.add(dummy_quest);
        questList.add(dummy_quest);

//        questList = Server.getQuests(((MainActivity) getActivity()).user);

        adapter.setTasks(questList);

        return view;
    }

    @Override
    public void handleDialogClose(DialogInterface dialog){
        Quest dummy_quest = new Quest();
        dummy_quest.setId(1l);
        dummy_quest.setName("Dummy Quest");

        questList.add(dummy_quest);
        questList.add(dummy_quest);
        questList.add(dummy_quest);

//        questList = Server.getQuests(user);
        Collections.reverse(questList);
        adapter.setTasks(questList);
        adapter.notifyDataSetChanged();
    }
}