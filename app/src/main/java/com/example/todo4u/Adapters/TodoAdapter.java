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

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {

    private OnClickListener listener;
    private ArrayList<Todo> todos;

    public TodoAdapter(){
        todos = new ArrayList<>();
        todos.add(new Todo(1, "title1", new Date(1,1,1), "description",
                new Reminder("now"), new Board("board", "des",
                new Member("id", "hello")), new Member("id", "hello")));
        todos.add(new Todo(1, "title1", new Date(1,1,1), "description",
                new Reminder("now"), new Board("board", "des",
                new Member("id", "hello")), new Member("id", "hello")));

        todos.add(new Todo(1, "title1", new Date(1,1,1), "description",
                new Reminder("now"), new Board("board", "des",
                new Member("id", "hello")), new Member("id", "hello")));

        todos.add(new Todo(1, "title1", new Date(1,1,1), "description",
                new Reminder("now"), new Board("board", "des",
                new Member("id", "hello")), new Member("id", "hello")));

        todos.add(new Todo(1, "title1", new Date(1,1,1), "description",
                new Reminder("now"), new Board("board", "des",
                new Member("id", "hello")), new Member("id", "hello")));

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

        ViewHolder(View itemView){
            super(itemView);
            this.todoName = itemView.findViewById(R.id.todo_title);
            this.todoDeadline = itemView.findViewById(R.id.todo_deadline);
            this.todoDescription = itemView.findViewById(R.id.todo_description);
            itemView.setOnClickListener(v -> listener.onClick(todos.get(getBindingAdapterPosition())));
        }
    }

    public interface OnClickListener {
        void onClick(Todo board);
    }
}
