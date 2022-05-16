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
import com.example.todo4u.Adapters.MemberAdapter;
import com.example.todo4u.R;

public class MembersFragment extends Fragment {

    RecyclerView membersList;
    MemberAdapter memberAdapter;
    Context context;

    public MembersFragment() {
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
        View view = inflater.inflate(R.layout.fragment_members, container, false);
        membersList = view.findViewById(R.id.recyclerViewMember);
        membersList.hasFixedSize();
        membersList.setLayoutManager(new LinearLayoutManager(this.getContext()));

        memberAdapter = new MemberAdapter();

        memberAdapter.getMemberData();
        membersList.setAdapter(memberAdapter);



        return view;
    }
}