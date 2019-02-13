package com.example.flo.projetandroid;

import android.arch.paging.PagedList;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.firestore.SnapshotParser;
import com.firebase.ui.firestore.paging.FirestorePagingAdapter;
import com.firebase.ui.firestore.paging.FirestorePagingOptions;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class ListEventsFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private FirestorePagingAdapter mAdapter;
    private FirebaseFirestore mFirebaseFirestore;

    public ListEventsFragment() {
        // Required empty public constructor
    }

    public static ListEventsFragment newInstance() {
        return new ListEventsFragment();
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
                .setQuery(query, config, new SnapshotParser<Event>() {
                    @NonNull
                    @Override
                    public Event parseSnapshot(@NonNull DocumentSnapshot snapshot) {
                        Event evt = new Event(
                                snapshot.getId(),
                                snapshot.getString("titre"),
                                snapshot.getString("sport"),
                                snapshot.getString("lieu"),
                                snapshot.get("date", Timestamp.class),
                                snapshot.get("dateLimit", Timestamp.class));
                        Log.i("TEST", evt.toString());
                        return evt;
                    }
                })
                .build();

        mAdapter = new EventFirestorePagingAdapter(options, getContext());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
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
        void onFragmentInteraction(Uri uri);
    }
}
