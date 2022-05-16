package com.example.todo4u.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo4u.Models.Board;
import com.example.todo4u.Models.Member;
import com.example.todo4u.Models.Reminder;
import com.example.todo4u.Models.Todo;
import com.example.todo4u.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {

    private OnClickListener listener;
    private ArrayList<Todo> todos;

    public TodoAdapter(){
        todos = new ArrayList<>();
        todos.add(new Todo( "Cooking", new GregorianCalendar(2022,5,18), "Need to cook dinner",
                new Reminder("Tomorrow"), new Board("Home chores", "Work around the house",
                new Member("i", "Milan Tolnai")), new Member("id", "Kyra Tolnai")));
        todos.add(new Todo( "Take the dog out on a walk", new GregorianCalendar(2022,5,18), "Billie is super annoying if you dont take him out",
                new Reminder("Tomorrow"), new Board("Home chores", "Work around the house",
                new Member("id", "Kyra Tolnai")), new Member("id", "Kyra Tolnai")));

        todos.add(new Todo( "Do assignment for ADS", new GregorianCalendar(2022,5,20), "Document the assignment",
                new Reminder("Day after tomorrow"), new Board("School", "des",
                new Member("id", "Kyra Tolnai")), new Member("id", "Kyra Tolnai")));
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.fragment_todo_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.todoName.setText(todos.get(position).getTitle());
        holder.todoDeadline.setText(todos.get(position).getDeadline().toString());
        holder.todoDescription.setText(todos.get(position).getDescription());
        holder.boardName.setText(todos.get(position).getBoard().getBoardName());
        holder.reminderName.setText(todos.get(position).getReminder().getType());
    }

    @Override
    public int getItemCount() {
        return todos.size();
    }

    public void setOnClickListener(TodoAdapter.OnClickListener listener){
        this.listener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView todoName;
        private final TextView todoDeadline;
        private final TextView todoDescription;
        private final TextView boardName;
        private final TextView reminderName;

        ViewHolder(View itemView){
            super(itemView);
            this.todoName = itemView.findViewById(R.id.todo_title);
            this.todoDeadline = itemView.findViewById(R.id.todo_deadline);
            this.todoDescription = itemView.findViewById(R.id.todo_description);
            this.boardName = itemView.findViewById(R.id.boardName);
            this.reminderName = itemView.findViewById(R.id.reminder);
            itemView.setOnClickListener(v -> listener.onClick(todos.get(getBindingAdapterPosition())));
        }
    }

    public interface OnClickListener {
        void onClick(Todo board);
    }
}
