package com.example.habitimia.ui.guild;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.habitimia.R;
import com.example.habitimia.ui.MainActivity;
import com.example.habitimia.ui.arena.RankingFragment;
import com.example.habitimia.ui.home.HomeFragment;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChatFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChatFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ImageButton Back;
    private LinearLayout ChatContent;

    public ChatFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChatFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChatFragment newInstance(String param1, String param2) {
        ChatFragment fragment = new ChatFragment();
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
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        Back = (ImageButton) view.findViewById(R.id.back_chat);
        ChatContent = view.findViewById(R.id.ChatLinearLayout);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).LoadFragment(new GuildFragment());
                ((MainActivity) getActivity()).setBottomBarVisibility(true);
            }
        });

        ArrayList<String> messages = new ArrayList<>();
        messages.add("Pls fix the dryer already");
        messages.add("Thanks for the reminder Ian, but I think you are fully capable of doing it yourself so gl, bro");
        messages.add("k.");
        messages.add("Pls fix the dryer already");
        messages.add("Thanks for the reminder Ian, but I think you are fully capable of doing it yourself so gl, bro");
        messages.add("k.");
        messages.add("Pls fix the dryer already");
        messages.add("Thanks for the reminder Ian, but I think you are fully capable of doing it yourself so gl, bro");
        messages.add("k.");

        ArrayList<String> authors = new ArrayList<>();
        authors.add("Ian");
        authors.add("Self");
        authors.add("Ian");
        authors.add("Ian");
        authors.add("Self");
        authors.add("Ian");
        authors.add("Ian");
        authors.add("Self");
        authors.add("Ian");

        ArrayList<Boolean> isSelf = new ArrayList<>();
        isSelf.add(false);
        isSelf.add(true);
        isSelf.add(false);
        isSelf.add(false);
        isSelf.add(true);
        isSelf.add(false);
        isSelf.add(false);
        isSelf.add(true);
        isSelf.add(false);

        // Ajout des messages dans le chat box
        FragmentManager fragMan = getFragmentManager();
        FragmentTransaction fragTransaction = fragMan.beginTransaction();

        for(int fragCount=0; fragCount < messages.size(); fragCount++) {
            MessageFragment messageFragment = MessageFragment.newInstance(messages.get(fragCount), authors.get(fragCount), isSelf.get(fragCount));

            fragTransaction.add(ChatContent.getId(), messageFragment , "messageFragment" + fragCount);

        }

        fragTransaction.commit();

        ((MainActivity) getActivity()).setBottomBarVisibility(false);

        return view;
    }
}