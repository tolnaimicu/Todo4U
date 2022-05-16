package com.example.todo4u.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.todo4u.Adapters.TodoAdapter;
import com.example.todo4u.R;

public class TodosFragment extends Fragment {

    RecyclerView todosList;
    TodoAdapter todoAdapter;
    Context context;
    Button addTodo;

    public TodosFragment() {
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
        View view = inflater.inflate(R.layout.fragment_todos, container, false);

        todosList = view.findViewById(R.id.recyclerViewTodos);
        todosList.hasFixedSize();
        todosList.setLayoutManager(new LinearLayoutManager(this.getContext()));

        todoAdapter = new TodoAdapter();
        todoAdapter.getTodoData();
        todosList.setAdapter(todoAdapter);


        addTodo =  view.findViewById(R.id.addTodo);

        addTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(TodosFragment.this).navigate(R.id.fragment_create_todo);
            }
        });



        return view;
    }
}