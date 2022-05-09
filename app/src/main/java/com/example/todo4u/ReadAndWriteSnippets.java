package com.example.todo4u;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ReadAndWriteSnippets {

    private static final String TAG = "ReadAndWriteSnippets";
    private DatabaseReference mDatabase;

    public ReadAndWriteSnippets(DatabaseReference databaseReference){
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public void writeNewUserWithTaskListeners(String userId, String name) {
        User user = new User(name, userId);

        mDatabase.child("users").child(userId).setValue(user.Id)
                .addOnSuccessListener(aVoid -> {Log.d(TAG, "Successful ");

                })
                .addOnFailureListener(e -> Log.w(TAG, e.getMessage()));

        mDatabase.child("users").child(userId).setValue(user.name)
                .addOnSuccessListener(aVoid -> {Log.d(TAG, "Successful ");

                })
                .addOnFailureListener(e -> Log.w(TAG, e.getMessage()));
    }




}
