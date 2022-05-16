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

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.ViewHolder>{

    private OnClickListener listener;
    private ArrayList<Member> members;

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
}
