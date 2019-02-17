package com.example.flo.projetandroid;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class UsersInEventAdapter extends RecyclerView.Adapter<UsersInEventAdapter.UserViewHolder> {

    Context mContext;
    List<User> mUsers;

    public UsersInEventAdapter(Context context){
        mUsers = new ArrayList<>();
        mContext = context;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.user_info_card, viewGroup, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int i) {
        String name = mUsers.get(i).getNom() + " " + mUsers.get(i).getPrenom();
        holder.nom.setText(name);
        holder.mobile.setText(mUsers.get(i).getMobile());
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        TextView nom, mobile;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            nom = itemView.findViewById(R.id.userName);
            mobile = itemView.findViewById(R.id.userMobile);
        }
    }

    public void addUser(User user){
        mUsers.add(user);
        notifyDataSetChanged();
    }
}
