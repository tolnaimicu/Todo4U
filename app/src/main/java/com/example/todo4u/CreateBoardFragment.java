package com.example.todo4u;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class CreateBoardFragment extends Fragment {


    private Button createBoardButton;
    private TextView boardTitle;
    private TextView boardDescription;
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance("https://todo4u-16517-default-rtdb.europe-west1.firebasedatabase.app/").getReference();
    private static final String TAG = "CreateBoardFragment";


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
                String usedId = FirebaseAuth.getInstance().getCurrentUser().getTenantId();
                String userName = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();

                Board board = new Board(title, description, new Member(usedId, userName));

                mDatabase
                        .child("board")
                        .child(usedId)
                        .setValue(board)
                        .addOnSuccessListener(aVoid -> {Log.d(TAG, "Successfully added to the database ");

                })
                        .addOnFailureListener(e -> Log.w(TAG, "Cannot add board to the database"));

            }
        });
        return view;
    }
}