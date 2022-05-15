package com.example.todo4u;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    NavController navController;
    AppBarConfiguration appBarConfiguration;
    DrawerLayout drawerLayout;
    NavigationView navigationDrawer;
    BottomNavigationView bottomNavigationView;
    Toolbar toolbar;
    private static final String TAG = "MainActivity";
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance("https://todo4u-16517-default-rtdb.europe-west1.firebasedatabase.app/").getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkCurrentUser();
        setContentView(R.layout.activity_main);
        initViews();
        setupNavigation();
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

    public void signOut() {
        AuthUI.getInstance().signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        startLoginActivity();
                    }
                });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_bar_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_logout:
                signOut();
                return true;
            default:
                return NavigationUI.onNavDestinationSelected(item, navController) || super.onOptionsItemSelected(item);
    }}

    private void initViews() {
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationDrawer = findViewById(R.id.navigation_drawer);
        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        toolbar = findViewById(R.id.toolbar);
    }

    public void writeNewMemberToDatabase() {

       String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
       String name = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();

        mDatabase
                .child("member")
                .child(userId)
                .setValue(name)
                .addOnSuccessListener(aVoid -> {
                    Log.d(TAG, "Successfully added to the database ");

                })
                .addOnFailureListener(e -> Log.w(TAG, "Cannot add member to the database"));
    }


    private void setupNavigation() {
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        setSupportActionBar(toolbar);

        NavigationUI.setupWithNavController(bottomNavigationView, navController);
        NavigationUI.setupWithNavController(navigationDrawer, navController);
    }

}

