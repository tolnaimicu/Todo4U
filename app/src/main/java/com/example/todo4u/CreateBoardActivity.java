package com.example.todo4u;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;


public class CreateBoardActivity extends AppCompatActivity {

    private Button createBoardButton;
    private TextView boardTitle;
    private TextView boardDescription;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_board);

        boardTitle = findViewById(R.id.board_title_textView);
        boardDescription = findViewById(R.id.board_desc_textView);
        createBoardButton = findViewById(R.id.create_board_button);

        createBoardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = boardTitle.getText().toString();
                String description = boardTitle.getText().toString();
                String usedId = FirebaseAuth.getInstance().getTenantId();

                //Board board = new Board(title,description,usedId);
            }
        });



    }
}

