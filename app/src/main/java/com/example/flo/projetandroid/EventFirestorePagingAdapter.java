package com.example.flo.projetandroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.firebase.ui.firestore.paging.FirestorePagingAdapter;
import com.firebase.ui.firestore.paging.FirestorePagingOptions;
import com.google.firebase.firestore.DocumentSnapshot;

public class EventFirestorePagingAdapter extends FirestorePagingAdapter<Event, EventFirestorePagingAdapter.EventViewHolder> {

    private Context mContext;
    private Fragment mFragment;

    public EventFirestorePagingAdapter(@NonNull FirestorePagingOptions<Event> options, Context context, Fragment fragment) {
        super(options);
        this.mContext = context;
        this.mFragment = fragment;
    }

    public class EventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView titre, sport, lieu, date, dateLimite;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);

            titre = itemView.findViewById(R.id.titreRecyclerView);
            sport = itemView.findViewById(R.id.sportRecyclerView);
            lieu = itemView.findViewById(R.id.lieuRecyclerView);
            date = itemView.findViewById(R.id.dateRecyclerView);
            dateLimite = itemView.findViewById(R.id.dateLimiteRecyclerView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            DocumentSnapshot documentSnapshot = getCurrentList().get(getAdapterPosition());
            Event event = documentSnapshot.toObject(Event.class);
            Integer userSize = event.getUsers() == null ? 0 : event.getUsers().size();
            Toast.makeText(mContext, "OnClick on position : " + getAdapterPosition() +
                    "\nTest : " + event.getTitre() +
                    "\nId : " + documentSnapshot.getId() +
                    "\nUsers size : " + userSize,
                    Toast.LENGTH_LONG).show();
            if(mFragment instanceof SwitchDocumentActivity){
                ((SwitchDocumentActivity) mFragment).goToActivity(documentSnapshot);
            }
        }
    }

    @Override
    protected void onBindViewHolder(@NonNull EventViewHolder holder, int position, @NonNull Event model) {
        holder.titre.setText(model.getTitre());
        holder.sport.setText(model.getSport());
        holder.lieu.setText(model.getLieu());
        holder.date.setText(model.getDate().toString());
        holder.dateLimite.setText(model.getDateLimite().toString());
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recyclerview_card_row, viewGroup, false);
        return new EventViewHolder(view);
    }
}
