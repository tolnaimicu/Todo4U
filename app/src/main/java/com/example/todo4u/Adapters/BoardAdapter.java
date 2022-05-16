package com.example.todo4u.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo4u.Models.Board;
import com.example.todo4u.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {

    ArrayList<Board> boards2;
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance("https://todo4u-16517-default-rtdb.europe-west1.firebasedatabase.app/").getReference();
    private OnClickListener listener;
    Context context;

    public BoardAdapter(){
        /// TODO: 13.05.2022 get boards out of database
       /* boards = new ArrayList<>();
        boards.add(new Board("board1", "description1",
                new Member("id1","nickname1")));
        boards.add(new Board("board2", "description2",
                new Member("id2","nickname2")));
        boards.add(new Board("board3", "description3",
                new Member("id3","nickname3")));
        boards.add(new Board("board4", "description4",
                new Member("id4","nickname4")));

        */
        boards2 = new ArrayList<Board>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        context = parent.getContext();
        View view = inflater.inflate(R.layout.fragment_board_item, parent, false);
        return new ViewHolder(view);


    }


    public void getBoardData()
    {
        databaseReference.child("board").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds: snapshot.getChildren())
                {
                    boards2.add(ds.getValue(Board.class));
                }

                notifyDataSetChanged();
                for (int i=0; i<boards2.size(); i++)
                {
                    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!"+boards2.get(i).boardName);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(context, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return boards2.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.boardName.setText(boards2.get(position).getBoardName());
        holder.boardOwner.setText(boards2.get(position).getOwner().getNickName());
        holder.boardDescription.setText(boards2.get(position).getBoardDescription());
    }


    public void setOnClickListener(OnClickListener listener){
        this.listener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView boardName;
        TextView boardOwner;
        TextView boardDescription;

        ViewHolder(View itemView){
            super(itemView);
            this.boardName = itemView.findViewById(R.id.board_name);
            this.boardOwner = itemView.findViewById(R.id.board_owner);
            this.boardDescription = itemView.findViewById(R.id.board_description);
            itemView.setOnClickListener(v -> listener.onClick(boards2.get(getBindingAdapterPosition())));


        }
    }

    public interface OnClickListener {
        void onClick(Board board);
    }

}
