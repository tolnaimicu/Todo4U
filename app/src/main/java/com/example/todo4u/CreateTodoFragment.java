package com.example.todo4u;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Locale;


public class CreateTodoFragment extends Fragment {

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

    public CreateTodoFragment() {
        // Required empty public constructor
    }

    Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = container.getContext();
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_create_todo, container, false);

        mDisplayDate = view.findViewById(R.id.tvDate);
        timeButton = view.findViewById(R.id.timeButton);
        createTodoBtn = view.findViewById(R.id.create_todo_btn);

        createTodoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        //Stuff for boards
        autoCompleteTextViewBoards = view.findViewById(R.id.select_board);
        adapterItemsBoards = new ArrayAdapter<String>(context, R.layout.list_item, boards);
        autoCompleteTextViewBoards.setAdapter(adapterItemsBoards);
        autoCompleteTextViewBoards.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(context, "Board: " + item, Toast.LENGTH_LONG).show();
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
                        context,
                        android.R.style.Widget_Material_Light_ActionBar,
                        mDateSetListener,
                        year, month, day);
                dialog.show();
            }
        });

        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popTimePicker(view);
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
        return view;
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

        TimePickerDialog timePickerDialog = new TimePickerDialog(context, /*style,*/ onTimeSetListener, hour, minute, true);

        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }
}