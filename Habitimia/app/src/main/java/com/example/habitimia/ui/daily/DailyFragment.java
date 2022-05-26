package com.example.habitimia.ui.daily;

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
import com.example.habitimia.data.adapter.DailyAdapter;
import com.example.habitimia.data.adapter.QuestAdapter;
import com.example.habitimia.data.model.AdventurerClass;
import com.example.habitimia.data.model.Daily;
import com.example.habitimia.data.model.Day;
import com.example.habitimia.data.model.Quest;
import com.example.habitimia.data.model.User;
import com.example.habitimia.ui.MainActivity;
import com.example.habitimia.ui.home.HomeFragment;
import com.example.habitimia.util.RecyclerItemDailyTouchHelper;
import com.example.habitimia.util.RecyclerItemTouchHelper;
import com.example.habitimia.util.Server;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DailyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DailyFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private ImageButton Back;
    private User user;
    private List<Daily> dailiesList;

    private RecyclerView tasksRecyclerView;
    private DailyAdapter adapter;
    public DailyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DailyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DailyFragment newInstance(String param1, String param2) {
        DailyFragment fragment = new DailyFragment();
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
        View view = inflater.inflate(R.layout.fragment_daily, container, false);
        Back = (ImageButton) view.findViewById(R.id.back_daily);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).LoadFragment(new HomeFragment());
            }
        });
        ((MainActivity) getActivity()).setBackgroundColor(getResources().getColor(R.color.white));
        ((MainActivity) getActivity()).UpdateFABIcon(R.drawable.ic_add);

        dailiesList = new ArrayList<>();

        tasksRecyclerView = view.findViewById(R.id.tasksRecyclerView);
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new DailyAdapter(((MainActivity) getActivity()));
        tasksRecyclerView.setAdapter(adapter);

        ItemTouchHelper itemTouchHelper = new
                ItemTouchHelper(new RecyclerItemDailyTouchHelper(adapter));
        itemTouchHelper.attachToRecyclerView(tasksRecyclerView);

        Daily dummy_daily = new Daily();
        dummy_daily.setId(1l);
        dummy_daily.setName("Dummy Quest Name");
        dummy_daily.setDetails("Dummy Quest Details");
        dummy_daily.setDifficulty(AdventurerClass.B);

        dailiesList.add(dummy_daily);
        dailiesList.add(dummy_daily);
        dailiesList.add(dummy_daily);

//        String current_day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        LocalDate date = null;
        DayOfWeek dow = null;
        Day day = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            date = LocalDate.now();
            dow = date.getDayOfWeek();

        }
        if (dow != null){
            day = Day.valueOf(dow.name());
        }
        dailiesList = Server.getDailiesForDay(((MainActivity) getActivity()).user, day);

        adapter.setTasks(dailiesList);


//        Daily daily = Server.updateDaily(dailies.get(0), "renaming test", null, null, null);
//        List<User> usersFromGuild = Server.getAllUsersForGuild(((MainActivity) getActivity()).user.getGuild());
//        User user_test = Server.register("Test", "", "123456", "MAGICIAN");
//        User same = Server.updateUserHP(user_test, 122L);
        return view;
    }
}