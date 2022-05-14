package com.example.todo4u.Fragments;

import android.app.UiModeManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;


import com.example.todo4u.LogInActivity;
import com.example.todo4u.MainActivity;
import com.example.todo4u.R;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SettingsFragment extends Fragment {

    private UiModeManager uiModeManager;
    private Context context;
    private ToggleButton toggleButton;
    Button deleteAccount;
    FirebaseUser user;
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance("https://todo4u-16517-default-rtdb.europe-west1.firebasedatabase.app/").getReference();


    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        context = container.getContext();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        user = FirebaseAuth.getInstance().getCurrentUser();

        deleteAccount = view.findViewById(R.id.delete_profile_button);

        deleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteAccount();

            }
        });

        uiModeManager = (UiModeManager) context.getSystemService(Context.UI_MODE_SERVICE);

        toggleButton = view.findViewById(R.id.toggleButton);

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked)
                {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
                else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });

        return view;
    }


    public void deleteAccount()
    {
       databaseReference.child("member").child(FirebaseAuth.getInstance()
       .getCurrentUser().getUid()).removeValue();

       FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

       user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
           @Override
           public void onComplete(@NonNull Task<Void> task) {
               if (task.isSuccessful()) {
                   Log.d("Settings", "User account deleted.");
                   Toast.makeText(context, "USer successfully deleted", Toast.LENGTH_LONG).show();
               }
           }
       });
    }




}