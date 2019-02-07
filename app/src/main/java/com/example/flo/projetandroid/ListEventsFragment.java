package com.example.flo.projetandroid;

import android.arch.paging.PagedList;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.paging.FirestorePagingAdapter;
import com.firebase.ui.firestore.paging.FirestorePagingOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class ListEventsFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private FirestorePagingAdapter<Event, EventViewHolder> mAdapter;
    private FirebaseFirestore mFirebaseFirestore;

    public ListEventsFragment() {
        // Required empty public constructor
    }

    public static ListEventsFragment newInstance() {
        return new ListEventsFragment();
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
            Event event = mAdapter.getCurrentList().get(getAdapterPosition()).toObject(Event.class);
            Toast.makeText(getContext(), "OnClick on position : " + getAdapterPosition() + " \nTest : " + event.getTitre(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirebaseFirestore = FirebaseFirestore.getInstance();

        Query query = mFirebaseFirestore.collection("events");

        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPrefetchDistance(10)
                .setPageSize(20)
                .build();

        FirestorePagingOptions<Event> options = new FirestorePagingOptions.Builder<Event>()
                .setLifecycleOwner(this)
                .setQuery(query, config, Event.class)
                .build();

        mAdapter = new FirestorePagingAdapter<Event, EventViewHolder>(options) {

            @NonNull
            @Override
            public EventViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(getContext()).inflate(R.layout.recyclerview_card_row, viewGroup, false);
                return new EventViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull EventViewHolder holder, int position, @NonNull Event model) {
                holder.titre.setText(model.getTitre());
                holder.sport.setText(model.getSport());
                holder.lieu.setText(model.getLieu());
                holder.date.setText(model.getDate().toDate().toString());
            }
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list_events, container, false);


        RecyclerView mRecyclerView = view.findViewById(R.id.recyclerViewListEvent);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onStart() {
        super.onStart();
        mAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        mAdapter.stopListening();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
