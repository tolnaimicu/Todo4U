package com.example.todo4u.Fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
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

import com.example.todo4u.Models.Board;
import com.example.todo4u.Models.Todo;
import com.example.todo4u.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Map;


public class CreateTodoFragment extends Fragment {

    private Button mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    private DatabaseReference databaseReference = FirebaseDatabase.getInstance("https://todo4u-16517-default-rtdb.europe-west1.firebasedatabase.app/").getReference();
    FirebaseDatabase firebaseDatabase;

    Button timeButton;
    int hour, minute;

    String[] boards = {"School", "Friends", "Surprise party"};
    AutoCompleteTextView autoCompleteTextViewBoards;
    ArrayAdapter<String> adapterItemsBoards;

    AutoCompleteTextView autoCompleteTextViewMembers;
    ArrayAdapter<String> adapterItemsMembers;

    String[] reminders = {"1 week before", "1 day before", "1 "};
    AutoCompleteTextView autoCompleteTextViewReminder;
    ArrayAdapter<String> adapterItemsReminders;

    int year, month, day;

    Button createTodoBtn;

    String selectedBoard;
    String selectedMember;
    String selectedReminder;

    String[] listOfNames;

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

        firebaseDatabase = FirebaseDatabase.getInstance();
        getDataMembers();

        mDisplayDate = view.findViewById(R.id.tvDate);
        timeButton = view.findViewById(R.id.timeButton);
        createTodoBtn = view.findViewById(R.id.create_todo_btn);


        createTodoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userId;
               GregorianCalendar date =  new GregorianCalendar(year, month, day, hour, minute, 0);
               String title = view.findViewById(R.id.todo_title).getContext().toString();
               String description = view.findViewById(R.id.todo_description).getContext().toString();

               //Need to get the board from the adapter with the help of the boardTitle
                //Need to get member through adapter with the memberName

               //Todo todo = new Todo(1,title,date,description,selectedReminder,selectedBoard,)


            }
        });

        //Stuff for boards
        autoCompleteTextViewBoards = view.findViewById(R.id.select_board);
        adapterItemsBoards = new ArrayAdapter<String>(context, R.layout.list_item, boards);
        autoCompleteTextViewBoards.setAdapter(adapterItemsBoards);
        autoCompleteTextViewBoards.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedBoard = parent.getItemAtPosition(position).toString();
                Toast.makeText(context, "Board: " + selectedBoard, Toast.LENGTH_LONG).show();
            }
        });

        //Stuff for members
        autoCompleteTextViewMembers = view.findViewById(R.id.select_member);
        adapterItemsMembers= new ArrayAdapter<String>(context, R.layout.list_item, listOfNames);
        autoCompleteTextViewMembers.setAdapter(adapterItemsMembers);
        autoCompleteTextViewMembers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedMember = parent.getItemAtPosition(position).toString();
                Toast.makeText(context, "Member: " + selectedMember, Toast.LENGTH_LONG).show();
            }
        });

        //Stuff for boards
        autoCompleteTextViewReminder = view.findViewById(R.id.select_Reminder);
        adapterItemsReminders = new ArrayAdapter<String>(context, R.layout.list_item, reminders);
        autoCompleteTextViewReminder.setAdapter(adapterItemsReminders);
        autoCompleteTextViewReminder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedReminder = parent.getItemAtPosition(position).toString();
                Toast.makeText(context, "Reminder: " + selectedReminder, Toast.LENGTH_LONG).show();
            }
        });

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                 year = cal.get(Calendar.YEAR);
                 month = cal.get(Calendar.MONTH);
                day = cal.get(Calendar.DAY_OF_MONTH);

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

    private void getDataMembers() {


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Map<String,String> value =(Map<String, String>)snapshot.getValue();


                listOfNames
                        = value.values().toArray(new String[0]);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // calling on cancelled method when we receive
                // any error or we are not able to get the data.
                Toast.makeText(context, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });
    }

   }