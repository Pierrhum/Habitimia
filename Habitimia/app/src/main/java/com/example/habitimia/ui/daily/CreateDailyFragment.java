package com.example.habitimia.ui.daily;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

import com.example.habitimia.R;
import com.example.habitimia.data.model.AdventurerClass;
import com.example.habitimia.data.model.Daily;
import com.example.habitimia.data.model.Day;
import com.example.habitimia.data.model.OwnerType;
import com.example.habitimia.data.model.Quest;
import com.example.habitimia.data.model.Repetition;
import com.example.habitimia.ui.MainActivity;
import com.example.habitimia.ui.guild.GuildFragment;
import com.example.habitimia.util.Server;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateDailyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateDailyFragment extends Fragment {

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

    private ArrayList<Button> RepetitionBtn;

    private Button Create;

    private int previousClassID = -1;
    private boolean[] selectedDays = new boolean[]{false,false,false,false,false,false,false};

    private Daily daily;
    private boolean isNewDaily = true;
    public OwnerType ownerType = OwnerType.User;

    public CreateDailyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreateDailyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateDailyFragment newInstance(String param1, String param2) {
        CreateDailyFragment fragment = new CreateDailyFragment();
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
        View view = inflater.inflate(R.layout.fragment_create_daily, container, false);
        Back = (ImageButton) view.findViewById(R.id.back_create_daily);
        Name = (EditText) view.findViewById(R.id.DailyName);
        Details = (EditText) view.findViewById(R.id.DailyDetails);



        ClassBtn = new ArrayList<Button>();
        ClassBtn.add((Button) view.findViewById(R.id.Class_F_Daily));
        ClassBtn.add((Button) view.findViewById(R.id.Class_E_Daily));
        ClassBtn.add((Button) view.findViewById(R.id.Class_D_Daily));
        ClassBtn.add((Button) view.findViewById(R.id.Class_C_Daily));
        ClassBtn.add((Button) view.findViewById(R.id.Class_B_Daily));
        ClassBtn.add((Button) view.findViewById(R.id.Class_A_Daily));
        ClassBtn.add((Button) view.findViewById(R.id.Class_S_Daily));
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
        RepetitionBtn = new ArrayList<Button>();
        RepetitionBtn.add((Button) view.findViewById(R.id.Monday));
        RepetitionBtn.add((Button) view.findViewById(R.id.Tuesday));
        RepetitionBtn.add((Button) view.findViewById(R.id.Wednesday));
        RepetitionBtn.add((Button) view.findViewById(R.id.Thursday));
        RepetitionBtn.add((Button) view.findViewById(R.id.Friday));
        RepetitionBtn.add((Button) view.findViewById(R.id.Saturday));
        RepetitionBtn.add((Button) view.findViewById(R.id.Sunday));
        cpt =0;
        for(Button b : RepetitionBtn) {
            int id = cpt;
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (! selectedDays[id]){
                        SelectChosenDay(id);
                    }
                    else{
                        DeselectChosenDay(id);
                    }
                }});
            cpt++;
        }

        Create = (Button) view.findViewById(R.id.createDaily);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    ((MainActivity) getActivity()).LoadFragment(new DailyFragment());

//                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.framecontainer, new QuestFragment()).commit();
            }
        });

        Create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=Name.getText().toString(), details=Details.getText().toString();
                if(name != "" && previousClassID != -1) {
                    Daily new_daily = new Daily(((MainActivity) getActivity()).user, name, details, AdventurerClass.values()[previousClassID]);
                    List<Day> days = new ArrayList<>();
                    for (int i = 0; i < selectedDays.length; i++){
                        if (selectedDays[i])
                            days.add(Day.values()[i]);
                    }
                    if (isNewDaily){
                            Server.createDaily(((MainActivity) getActivity()).user, new_daily, days);

                    }else{
                        new_daily.setId(daily.getId());
                        Server.updateDaily(new_daily, days);
                        isNewDaily=true;
                    }

                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.framecontainer, new DailyFragment()).commit();
                }
            }
        });

        ((MainActivity) getActivity()).setBackgroundColor(getResources().getColor(R.color.white));

        Bundle bundle = this.getArguments();
        try {
            ownerType =  (OwnerType) bundle.getSerializable("ownerType");
            daily = (Daily) bundle.getSerializable("daily");

            fillData();
            isNewDaily = false;
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

    private void SelectChosenDay(int id) {

        RepetitionBtn.get(id).setBackgroundColor(getResources().getColor(R.color.difficulty_btn_on));
        RepetitionBtn.get(id).setTextColor(getResources().getColor(R.color.white));

        selectedDays[id] = true;
    }

    private void DeselectChosenDay(int id) {

        RepetitionBtn.get(id).setBackgroundColor(ClassColorBtn.get(id));
        RepetitionBtn.get(id).setTextColor(getResources().getColor(R.color.black));

        selectedDays[id] = false;
    }


    private void fillData() {
        Name.setText(daily.getName());
        Details.setText(daily.getDetails());
        UpdateChosenClass(daily.getDifficulty().ordinal());
        for (Repetition rep:daily.getRepetitions()){
            SelectChosenDay(rep.getDay().ordinal());
        }
//        UpdateChosenDay(daily.get().ordinal());
    }
}