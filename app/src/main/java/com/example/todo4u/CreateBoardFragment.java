package com.example.todo4u;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;


public class CreateBoardFragment extends Fragment {


    private Button createBoardButton;
    private TextView boardTitle;
    private TextView boardDescription;

    public CreateBoardFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_board, container, false);

        boardTitle = view.findViewById(R.id.board_title_textView);
        boardDescription = view.findViewById(R.id.board_desc_textView);
        createBoardButton = view.findViewById(R.id.create_board_button);

        createBoardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = boardTitle.getText().toString();
                String description = boardDescription.getText().toString();
                String usedId = FirebaseAuth.getInstance().getTenantId();

                //Need to implement Board creation here
            }
        });
        return view;
    }
}