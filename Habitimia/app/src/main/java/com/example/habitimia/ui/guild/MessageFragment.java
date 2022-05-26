package com.example.habitimia.ui.guild;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;

import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.habitimia.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MessageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MessageFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private String message;
    private String author;
    private boolean self;

    private TextView MessageText;
    private TextView AuthorText;
    private ImageView MessageBox;

    public MessageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MessageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MessageFragment newInstance(String message, String author, boolean self) {
        MessageFragment fragment = new MessageFragment();

        fragment.message = message;
        fragment.author = author;
        fragment.self = self;

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
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        MessageText = (TextView) view.findViewById(R.id.MessageText);
        AuthorText = (TextView) view.findViewById(R.id.MessageAuthor);
        MessageBox = (ImageView) view.findViewById(R.id.MessageBox);

        MessageText.setText(message);
        AuthorText.setText(author);
        AuthorText.setVisibility(self ? View.GONE : View.VISIBLE);

        MessageBox.setBackground(getResources().getDrawable(self ? R.drawable.self_message : R.drawable.member_message));
        MessageBox.setBackgroundTintList(ColorStateList.valueOf(self ?
                getResources().getColor(R.color.teal_200) :
                getResources().getColor(R.color.light_gray)));

        return view;
    }
}