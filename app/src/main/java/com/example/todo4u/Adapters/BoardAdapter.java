package com.example.todo4u.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo4u.Models.Board;
import com.example.todo4u.Models.Member;
import com.example.todo4u.R;

import java.util.ArrayList;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {

    private ArrayList<Board> boards;
    private OnClickListener listener;

    public BoardAdapter(){
        /// TODO: 13.05.2022 get boards out of database
        boards = new ArrayList<>();
        boards.add(new Board("School", "Must do my hand-in for AND",
                new Member("id1","Kyra Tolnai")));
        boards.add(new Board("Work", "Let's implement this new feature for our site",
                new Member("id2","Kyra Tolnai")));
        boards.add(new Board("SEP Project", "Need to do ETL and documentation",
                new Member("id3","Kyra Tolnai")));
        boards.add(new Board("Wellness", "All my tasks to make me feel better",
                new Member("id4","Kyra Tolnai")));
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.fragment_board_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.boardName.setText(boards.get(position).getBoardName());
        holder.boardOwner.setText(boards.get(position).getOwner().getNickName());
        holder.boardDescription.setText(boards.get(position).getBoardDescription());
    }

    @Override
    public int getItemCount() {
        return boards.size();
    }

    public void setOnClickListener(OnClickListener listener){
        this.listener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView boardName;
        private final TextView boardOwner;
        private final TextView boardDescription;

        ViewHolder(View itemView){
            super(itemView);
            this.boardName = itemView.findViewById(R.id.board_name);
            this.boardOwner = itemView.findViewById(R.id.board_owner);
            this.boardDescription = itemView.findViewById(R.id.board_description);
            itemView.setOnClickListener(v -> listener.onClick(boards.get(getBindingAdapterPosition())));
        }
    }

    public interface OnClickListener {
        void onClick(Board board);
    }
}
