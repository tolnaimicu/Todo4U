package com.example.todo4u;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance("https://todo4u-16517-default-rtdb.europe-west1.firebasedatabase.app/").getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkCurrentUser();
        setContentView(R.layout.activity_main);
    }

    // LOG IN, LOG OUT ----
    public void checkCurrentUser() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            Toast.makeText(this, "Hello " + user.getDisplayName(), Toast.LENGTH_SHORT).show();
            writeNewMemberToDatabase();
        } else {
            startLoginActivity();
        }
    }


    private void startLoginActivity() {
        startActivity(new Intent(this, LogInActivity.class));
        finish();
    }

    public void signOut(View w) {
        AuthUI.getInstance().signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        startLoginActivity();
                    }
                });
    }



    public void writeNewMemberToDatabase() {

       String userId = FirebaseAuth.getInstance().getCurrentUser().getTenantId();
       String name = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();

        mDatabase
                .child("member")
                .child(userId)
                .setValue(name)
                .addOnSuccessListener(aVoid -> {Log.d(TAG, "Successfully added to the database ");

                })
                .addOnFailureListener(e -> Log.w(TAG, "Cannot add member to the database"));
    }



}

