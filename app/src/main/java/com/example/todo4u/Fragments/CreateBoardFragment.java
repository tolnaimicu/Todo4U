package com.example.todo4u.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todo4u.Models.Board;
import com.example.todo4u.Models.Member;
import com.example.todo4u.Models.Todo;
import com.example.todo4u.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public class CreateBoardFragment extends Fragment {


    private Button createBoardButton;
    private TextView boardTitle;
    private TextView boardDescription;
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance("https://todo4u-16517-default-rtdb.europe-west1.firebasedatabase.app/").getReference();
    private static final String TAG = "CreateBoardFragment";
    Context context;


    public CreateBoardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        context = container.getContext();
        View view = inflater.inflate(R.layout.fragment_create_board, container, false);

        boardTitle = view.findViewById(R.id.board_title_textView);
        boardDescription = view.findViewById(R.id.board_desc_textView);
        createBoardButton = view.findViewById(R.id.create_board_button);



        createBoardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = boardTitle.getText().toString();
                String description = boardDescription.getText().toString();
                String usedId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                String userName = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();

                Board board = new Board(title, description, new Member(usedId, userName));

                databaseReference
                        .child("board").child(usedId).child(title)
                        .setValue(board)
                        .addOnSuccessListener(aVoid -> {Log.d(TAG, "Successfully added to the database ");

                })
                        .addOnFailureListener(e -> Log.w(TAG, "Cannot add board to the database"));

                NavHostFragment.findNavController(CreateBoardFragment.this).navigate(R.id.menu_myBoards);
            }
        });
        return view;
    }


}