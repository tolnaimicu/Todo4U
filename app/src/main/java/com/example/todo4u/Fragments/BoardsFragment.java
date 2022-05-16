package com.example.todo4u.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.example.todo4u.Adapters.BoardAdapter;
import com.example.todo4u.MainActivity;
import com.example.todo4u.R;

public class BoardsFragment extends Fragment {

    RecyclerView boardsList;
    BoardAdapter boardAdapter;
    Context context;
    Button button;
    public BoardsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = container.getContext();


        View view = inflater.inflate(R.layout.fragment_boards, container, false);

        button = view.findViewById(R.id.board_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(BoardsFragment.this).navigate(R.id.fragment_create_board);

            }
        });
        boardsList = view.findViewById(R.id.recyclerViewBoard);
        boardsList.hasFixedSize();
        boardsList.setLayoutManager(new LinearLayoutManager(this.getContext()));

        boardAdapter = new BoardAdapter();
        boardAdapter.getBoardData();
        boardsList.setAdapter(boardAdapter);


        boardAdapter.setOnClickListener(board -> {
            Toast.makeText(context, board.getBoardName(), Toast.LENGTH_SHORT).show();

                });



        return view;
    }
}