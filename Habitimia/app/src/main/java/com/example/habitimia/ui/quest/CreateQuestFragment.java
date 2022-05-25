package com.example.habitimia.ui.quest;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Debug;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.habitimia.R;
import com.example.habitimia.data.model.AdventurerClass;
import com.example.habitimia.data.model.OwnerType;
import com.example.habitimia.data.model.Quest;
import com.example.habitimia.data.model.User;
import com.example.habitimia.ui.MainActivity;
import com.example.habitimia.ui.guild.GuildFragment;
import com.example.habitimia.ui.home.HomeFragment;
import com.example.habitimia.util.Server;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateQuestFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateQuestFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ImageButton Back;
    private EditText Name;
    private EditText Details;
    private ArrayList<Button> ClassBtn;
    private ArrayList<Integer> ClassColorBtn;
    private Button Create;

    private int previousClassID = -1;

    private Quest quest;
    private boolean isNewQuest = true;
    public OwnerType ownerType = OwnerType.User;

    public CreateQuestFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreateQuestFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateQuestFragment newInstance(String param1, String param2) {
        CreateQuestFragment fragment = new CreateQuestFragment();
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
        Resources res = getResources();
        ClassColorBtn = new ArrayList<Integer>();
        ClassColorBtn.add(res.getColor(R.color.difficulty_btn_1));
        ClassColorBtn.add(res.getColor(R.color.difficulty_btn_2));
        ClassColorBtn.add(res.getColor(R.color.difficulty_btn_3));
        ClassColorBtn.add(res.getColor(R.color.difficulty_btn_4));
        ClassColorBtn.add(res.getColor(R.color.difficulty_btn_5));
        ClassColorBtn.add(res.getColor(R.color.difficulty_btn_6));
        ClassColorBtn.add(res.getColor(R.color.difficulty_btn_7));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_quest, container, false);
        Back = (ImageButton) view.findViewById(R.id.back_create_quest);
        Name = (EditText) view.findViewById(R.id.QuestName);
        Details = (EditText) view.findViewById(R.id.QuestDetails);



        ClassBtn = new ArrayList<Button>();
        ClassBtn.add((Button) view.findViewById(R.id.Class_F));
        ClassBtn.add((Button) view.findViewById(R.id.Class_E));
        ClassBtn.add((Button) view.findViewById(R.id.Class_D));
        ClassBtn.add((Button) view.findViewById(R.id.Class_C));
        ClassBtn.add((Button) view.findViewById(R.id.Class_B));
        ClassBtn.add((Button) view.findViewById(R.id.Class_A));
        ClassBtn.add((Button) view.findViewById(R.id.Class_S));
        int cpt =0;
        for(Button b : ClassBtn) {
            int id = cpt;
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UpdateChosenClass(id);
                }});
            cpt++;
        }

        Create = (Button) view.findViewById(R.id.createQuest);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.framecontainer, new QuestFragment()).commit();
            }
        });

        Create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=Name.getText().toString(), details=Details.getText().toString();
                if(name != "" && previousClassID != -1) {
                    Quest new_quest = new Quest(((MainActivity) getActivity()).user, name, details, AdventurerClass.values()[previousClassID]);
                    if (isNewQuest){
                        if (ownerType == OwnerType.User)
                            Server.createQuest(((MainActivity) getActivity()).user, new_quest);
                        else
                            Server.createQuest(((MainActivity) getActivity()).user.getGuild(), new_quest);
                    }else{
                        new_quest.setId(quest.getId());
                        Server.updateQuest(new_quest);
                        isNewQuest=true;
                    }
                    if (ownerType == OwnerType.User) {

                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.framecontainer, new QuestFragment()).commit();
                    }else {
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.framecontainer, new GuildFragment()).commit();
                    }
//                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.framecontainer, new QuestFragment()).commit();
                }
            }
        });

        ((MainActivity) getActivity()).setBackgroundColor(getResources().getColor(R.color.white));

        Bundle bundle = this.getArguments();
        try {
            ownerType =  (OwnerType) bundle.getSerializable("ownerType");
            quest = (Quest) bundle.getSerializable("quest");

            fillData();
            isNewQuest = false;
        }catch (Exception e){}

//        if (intent != null)
//            user = (User) intent.getSerializableExtra("user");

        return view;
    }


    private void UpdateChosenClass(int id) {
        if(previousClassID != -1)
        {
            ClassBtn.get(previousClassID).setBackgroundColor(ClassColorBtn.get(previousClassID));
            ClassBtn.get(previousClassID).setTextColor(getResources().getColor(R.color.black));
        }
        previousClassID = id;
        ClassBtn.get(id).setBackgroundColor(getResources().getColor(R.color.difficulty_btn_on));
        ClassBtn.get(id).setTextColor(getResources().getColor(R.color.white));
    }

    private void fillData() {
        Name.setText(quest.getName());
        Details.setText(quest.getDetails());
        UpdateChosenClass(quest.getDifficulty().ordinal());
    }
}