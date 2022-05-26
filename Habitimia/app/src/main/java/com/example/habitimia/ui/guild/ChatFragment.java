package com.example.habitimia.ui.guild;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.habitimia.R;
import com.example.habitimia.data.model.Message;
import com.example.habitimia.data.model.User;
import com.example.habitimia.ui.MainActivity;
import com.example.habitimia.ui.arena.RankingFragment;
import com.example.habitimia.ui.home.HomeFragment;
import com.example.habitimia.util.Server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    private ScrollView ScrollBox;
    private EditText ChatText;
    private ImageButton Send;



//    ArrayList<String> messages = new ArrayList<>();
//    ArrayList<String> authors = new ArrayList<>();
//    ArrayList<Boolean> isSelf = new ArrayList<>();

    List<Message> messages = new ArrayList<>();
    private TextView GuildName;

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
        Send = (ImageButton) view.findViewById(R.id.SendButton);
        ChatText = (EditText) view.findViewById(R.id.ChatText);
        ScrollBox = (ScrollView) view.findViewById(R.id.ChatScrollBox);
        ChatContent = (LinearLayout) view.findViewById(R.id.ChatLinearLayout);
        GuildName = (TextView) view.findViewById(R.id.chatName);
        GuildName.setText(((MainActivity) getActivity()).user.getGuild().getName());

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).LoadFragment(new GuildFragment());
                ((MainActivity) getActivity()).setBottomBarVisibility(true);
            }
        });
        messages = Server.getMessagesForPastWeek(((MainActivity) getActivity()).user.getGuild());
//        messages.add("Pls fix the dryer already");
//        messages.add("Thanks for the reminder Ian, but I think you are fully capable of doing it yourself so gl, bro");
//        messages.add("k.");
//        messages.add("Pls fix the dryer already");
//        messages.add("Thanks for the reminder Ian, but I think you are fully capable of doing it yourself so gl, bro");
//        messages.add("k.");
//        messages.add("Pls fix the dryer already");
//        messages.add("Thanks for the reminder Ian, but I think you are fully capable of doing it yourself so gl, bro");
//        messages.add("k.");
//
//        authors.add("Ian");
//        authors.add("Self");
//        authors.add("Ian");
//        authors.add("Ian");
//        authors.add("Self");
//        authors.add("Ian");
//        authors.add("Ian");
//        authors.add("Self");
//        authors.add("Ian");
//
//        isSelf.add(false);
//        isSelf.add(true);
//        isSelf.add(false);
//        isSelf.add(false);
//        isSelf.add(true);
//        isSelf.add(false);
//        isSelf.add(false);
//        isSelf.add(true);
//        isSelf.add(false);

//        ShowMessages(messages, authors, isSelf);
        ShowMessages(messages);

        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ChatText.getText().toString() != "") {
//                    messages.add(ChatText.getText().toString());
//                    authors.add("Self");
//                    isSelf.add(true);
                    Message m = new Message(ChatText.getText().toString(), ((MainActivity) getActivity()).user);
                    AddMessage(m, true);
                    ChatText.setText("");
                    (new Handler()).postDelayed(ChatFragment.this::ScrollDown, 100);
                }
            }
        });
        (new Handler()).postDelayed(this::ScrollDown, 100);
        ((MainActivity) getActivity()).setBottomBarVisibility(false);

        return view;
    }

    private void ShowMessages(ArrayList<String> messages, ArrayList<String> authors, ArrayList<Boolean> isSelf) {
        ChatContent.removeAllViews();

        // Ajout des messages dans le chat box
        FragmentManager fragMan = getFragmentManager();
        FragmentTransaction fragTransaction = fragMan.beginTransaction();

        for(int fragCount=0; fragCount < messages.size(); fragCount++) {
            MessageFragment messageFragment = MessageFragment.newInstance(messages.get(fragCount), authors.get(fragCount), isSelf.get(fragCount));

            fragTransaction.add(ChatContent.getId(), messageFragment , "messageFragment" + fragCount);

        }

        fragTransaction.commit();
    }
    private void ShowMessages(List<Message> messages) {
        ChatContent.removeAllViews();

        // Ajout des messages dans le chat box
        FragmentManager fragMan = getFragmentManager();
        FragmentTransaction fragTransaction = fragMan.beginTransaction();

        for(int fragCount=0; fragCount < messages.size(); fragCount++) {
            Message m = messages.get(fragCount);
//            boolean self = ((MainActivity) getActivity()).user.getId().equals(m.getUser().getId());
            MessageFragment messageFragment = MessageFragment.newInstance(m.getText(),
                                                        m.getUser().getUsername(),
                                                    m.getUser().getId().equals(((MainActivity) getActivity()).user.getId()));

            fragTransaction.add(ChatContent.getId(), messageFragment , "messageFragment" + fragCount);

        }

        fragTransaction.commit();
    }

    private void AddMessage(String message, String author, boolean isSelf) {
        // Ajout des messages dans le chat box
        FragmentManager fragMan = getFragmentManager();
        FragmentTransaction fragTransaction = fragMan.beginTransaction();

        MessageFragment messageFragment = MessageFragment.newInstance(message, author, isSelf);

        fragTransaction.add(ChatContent.getId(), messageFragment , "messageFragment" + ChatContent.getChildCount());

        fragTransaction.commit();
    }
    private void AddMessage(Message m, boolean isSelf) {

        Server.addMessage(m);
        // Ajout des messages dans le chat box
        FragmentManager fragMan = getFragmentManager();
        FragmentTransaction fragTransaction = fragMan.beginTransaction();


        MessageFragment messageFragment = MessageFragment.newInstance(m.getText(),
                m.getUser().getUsername(),
                m.getUser().getId().equals(((MainActivity) getActivity()).user.getId()));
//        User user = ((MainActivity) getActivity()).user;
//        boolean self = ((MainActivity) getActivity()).user.getId() == m.getUser().getId();
        fragTransaction.add(ChatContent.getId(), messageFragment , "messageFragment" +  ChatContent.getChildCount());

        fragTransaction.commit();
        ((MainActivity) getActivity()).LoadFragment(new ChatFragment());

    }
    private void ScrollDown() {
        ScrollBox.fullScroll(ScrollView.FOCUS_DOWN);
    }


}
