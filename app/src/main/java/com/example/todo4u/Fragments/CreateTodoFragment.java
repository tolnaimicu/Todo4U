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
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.todo4u.Models.Board;
import com.example.todo4u.Models.Member;
import com.example.todo4u.Models.Reminder;
import com.example.todo4u.Models.Todo;
import com.example.todo4u.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
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


    String selectedMember;
    String selectedReminder;
    String selectedMemberID;
    Board selectedBoardB;

    TextView todoTitle;
    TextView todoDesc;


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
        getDataBoards();


        mDisplayDate = view.findViewById(R.id.tvDate);
        timeButton = view.findViewById(R.id.timeButton);
        createTodoBtn = view.findViewById(R.id.create_todo_btn);

        todoTitle = view.findViewById(R.id.todo_title);
        todoDesc = view.findViewById(R.id.todo_description);

        createTodoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               GregorianCalendar date =  new GregorianCalendar(year, month, day, hour, minute, 0);
               String title = todoTitle.getText().toString();
               String description = todoDesc.getText().toString();
               int todoId;


               //Need to get the board from the adapter with the help of the boardTitle
                //Need to get member through adapter with the memberName

               Todo todo = new Todo(title,date,description,new Reminder(selectedReminder),selectedBoardB, new Member(selectedMemberID,selectedMember));

               databaseReference.child("todos").child(selectedMemberID).child(title).setValue(todo).addOnSuccessListener(aVoid -> {
                   Log.d("Todo", "Successfully added to the database ");
               }).addOnFailureListener(e -> Log.w("Todo", "Cannot add board to the database"));


            }
        });

        //Stuff for boards
        autoCompleteTextViewBoards = view.findViewById(R.id.select_board);


        //Stuff for members
        autoCompleteTextViewMembers = view.findViewById(R.id.select_member);

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

        databaseReference.child("member").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                HashMap<String,String> value =(HashMap<String, String>)snapshot.getValue();

               String[] array
                       = value.values().toArray(new String[0]);



                adapterItemsMembers= new ArrayAdapter<String>(context, R.layout.list_item, array);
                autoCompleteTextViewMembers.setAdapter(adapterItemsMembers);
                autoCompleteTextViewMembers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        selectedMember = parent.getItemAtPosition(position).toString();
                        for(int i=0;i<array.length;i++)
                        {
                            if(value.values().toArray(new String[0])[i].equals(selectedMember))
                                selectedMemberID = value.keySet().toArray(new String[0])[i];


                        }

                        Toast.makeText(context, "Member: " + selectedMember, Toast.LENGTH_LONG).show();
                    }
                });



            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // calling on cancelled method when we receive
                // any error or we are not able to get the data.
                Toast.makeText(context, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void getDataBoards() {

        databaseReference.child("board").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Board> borrads = new ArrayList<>();
                for(DataSnapshot ds: snapshot.getChildren())
                {
                    borrads.add(ds.getValue(Board.class));
                }

                HashMap<String,Board> value =(HashMap<String, Board>)snapshot.getValue();

                for(int i=0;i<borrads.size();i++)
                {
                    System.out.println(borrads.get(i).boardName);
                }

                String array[] = new String[borrads.size()];

                for(int i=0;i<borrads.size();i++)
                {
                    array[i] = borrads.get(i).boardName;
                }

                adapterItemsBoards = new ArrayAdapter<String>(context, R.layout.list_item, array);
                autoCompleteTextViewBoards.setAdapter(adapterItemsBoards);
                autoCompleteTextViewBoards.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String selectedBoard = parent.getItemAtPosition(position).toString();
                        Toast.makeText(context, "Board: " + selectedBoard, Toast.LENGTH_LONG).show();
                        for(int i=0;i<borrads.size();i++)
                        {
                            if(selectedBoard.equals(borrads.get(i).boardName))
                                selectedBoardB = borrads.get(i);
                        }
                    }
                });


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