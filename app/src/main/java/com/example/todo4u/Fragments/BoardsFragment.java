package com.example.todo4u.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.todo4u.Adapters.BoardAdapter;
import com.example.todo4u.R;

public class BoardsFragment extends Fragment {

    RecyclerView boardsList;
    BoardAdapter boardAdapter;
    Context context;

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
        boardsList = view.findViewById(R.id.recyclerViewBoard);
        boardsList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        boardsList.setLayoutManager(linearLayoutManager);
        boardAdapter = new BoardAdapter();
        boardAdapter.getBoardData();


        boardAdapter.setOnClickListener(board -> {
            Toast.makeText(context, board.getBoardName(), Toast.LENGTH_SHORT).show();
        });


        boardsList.setAdapter(boardAdapter);



        return view;
    }
}