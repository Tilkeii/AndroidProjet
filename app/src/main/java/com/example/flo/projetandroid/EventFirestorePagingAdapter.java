package com.example.flo.projetandroid;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.paging.FirestorePagingAdapter;
import com.firebase.ui.firestore.paging.FirestorePagingOptions;

public class EventFirestorePagingAdapter extends FirestorePagingAdapter<Event, EventFirestorePagingAdapter.EventViewHolder> {

    private Context mContext;

    public EventFirestorePagingAdapter(@NonNull FirestorePagingOptions<Event> options, Context context) {
        super(options);
        this.mContext = context;
    }

    public class EventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView titre, sport, lieu, date;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);

            titre = itemView.findViewById(R.id.titreRecyclerView);
            sport = itemView.findViewById(R.id.sportRecyclerView);
            lieu = itemView.findViewById(R.id.lieuRecyclerView);
            date = itemView.findViewById(R.id.dateRecyclerView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Event event = getCurrentList().get(getAdapterPosition()).toObject(Event.class);
            Toast.makeText(mContext, "OnClick on position : " + getAdapterPosition() + " \nTest : " + event.getTitre(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onBindViewHolder(@NonNull EventViewHolder holder, int position, @NonNull Event model) {
        holder.titre.setText(model.getTitre());
        holder.sport.setText(model.getSport());
        holder.lieu.setText(model.getLieu());
        holder.date.setText(model.getDate().toDate().toString());
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recyclerview_card_row, viewGroup, false);
        return new EventViewHolder(view);
    }
}
