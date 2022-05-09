package com.example.todo4u;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Locale;

public class CreateTodoActivity extends AppCompatActivity {

    private Button mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    Button timeButton;
    int hour, minute;

    String[] boards = {"School", "Friends", "Surprise party"};
    AutoCompleteTextView autoCompleteTextViewBoards;
    ArrayAdapter<String> adapterItemsBoards;

    String[] members = {"Me", "Bianca", "Milan"};
    AutoCompleteTextView autoCompleteTextViewMembers;
    ArrayAdapter<String> adapterItemsMembers;

    String[] reminders = {"1 week before", "1 day before", "1 "};
    AutoCompleteTextView autoCompleteTextViewReminder;
    ArrayAdapter<String> adapterItemsReminders;

    Button createTodoBtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_todo);
        mDisplayDate = (Button) findViewById(R.id.tvDate);
        timeButton = findViewById(R.id.timeButton);
        createTodoBtn = findViewById(R.id.create_todo_btn);

        createTodoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        //Stuff for boards
        autoCompleteTextViewBoards = findViewById(R.id.select_board);
        adapterItemsBoards = new ArrayAdapter<String>(CreateTodoActivity.this, R.layout.list_item, boards);
        autoCompleteTextViewBoards.setAdapter(adapterItemsBoards);
        autoCompleteTextViewBoards.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Board: " + item, Toast.LENGTH_LONG).show();
            }
        });

        //Stuff for members
        autoCompleteTextViewMembers = findViewById(R.id.select_member);
        adapterItemsMembers = new ArrayAdapter<String>(CreateTodoActivity.this, R.layout.list_item, members);
        autoCompleteTextViewMembers.setAdapter(adapterItemsMembers);
        autoCompleteTextViewMembers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Member: " + item, Toast.LENGTH_LONG).show();
            }
        });

        //Stuff for reminder
        autoCompleteTextViewReminder = findViewById(R.id.select_Reminder);
        adapterItemsReminders = new ArrayAdapter<String>(CreateTodoActivity.this, R.layout.list_item, reminders);
        autoCompleteTextViewReminder.setAdapter(adapterItemsReminders);
        autoCompleteTextViewReminder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Reminder: " + item, Toast.LENGTH_LONG).show();
            }
        });

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        CreateTodoActivity.this,
                        android.R.style.Widget_Material_Light_ActionBar,
                        mDateSetListener,
                        year, month, day);
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;

                String date = month + "/" + day + "/" + year;
                mDisplayDate.setText(date);
            }
        };
    }

    public void popTimePicker(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                hour = selectedHour;
                minute = selectedMinute;
                timeButton.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, minute));
            }
        };

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, /*style,*/ onTimeSetListener, hour, minute, true);

        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }
}
