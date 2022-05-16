package com.example.todo4u.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo4u.Models.Board;
import com.example.todo4u.Models.Member;
import com.example.todo4u.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.ViewHolder>{

    private OnClickListener listener;
    private ArrayList<Member> members;
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance("https://todo4u-16517-default-rtdb.europe-west1.firebasedatabase.app/").getReference();

    public MemberAdapter(){
        members = new ArrayList<>();
        members.add(new Member("hello","Maria-Bianca Militaru"));
        members.add(new Member("hello","Kyra Tolnai"));
        members.add(new Member("hello","Milan Tolnai"));
    }

    public void setOnClickListener(MemberAdapter.OnClickListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.fragment_member_item, parent, false);
        return new MemberAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nickName.setText(members.get(position).getNickName());
    }

    @Override
    public int getItemCount() {
        return members.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView nickName;

        ViewHolder(View itemView){
            super(itemView);
            this.nickName = itemView.findViewById(R.id.nick_name);
            itemView.setOnClickListener(v -> listener.onClick(members.get(getBindingAdapterPosition())));
        }
    }

    public interface OnClickListener {
        void onClick(Member member);
    }

    public void getMemberData()
    {
        databaseReference.child("member").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String,String> value =(HashMap<String, String>)snapshot.getValue();

                members
                        = value.values().toArray(new String[0]);

                notifyDataSetChanged();

                for (int i=0; i<members.length; i++)
                {
                    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!"+members[i]);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(context, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
