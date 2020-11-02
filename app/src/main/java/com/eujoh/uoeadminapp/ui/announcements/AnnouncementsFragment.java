package com.eujoh.uoeadminapp.ui.announcements;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eujoh.uoeadminapp.AddLostAndFound;
import com.eujoh.uoeadminapp.R;
import com.eujoh.uoeadminapp.adapters.AnnouncementsAdapter;
import com.eujoh.uoeadminapp.models.UploadsModel;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AnnouncementsFragment extends Fragment {
    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.
    ExtendedFloatingActionButton post_fab;
    RecyclerView recyclerView;

    private DatabaseReference databaseReference;
    private ArrayList<UploadsModel> announcementsList;
    private AnnouncementsAdapter recyclerAdapter;
    private Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_announcements, parent, false);

        post_fab = rootView.findViewById(R.id.fab_post);
        recyclerView = rootView.findViewById(R.id.announcement_recycler);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        //Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference();

//        ArrayList
        announcementsList = new ArrayList<>();

//        Clear ArrayList
            clearAll();

//        Get Data method
        GetDataMethodFromFirebase();
        return rootView;
    }

    private void GetDataMethodFromFirebase() {
        Query query = databaseReference.child("Announcements");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    UploadsModel uploadsModel = new UploadsModel();
                    uploadsModel.setImageURL(snapshot.child("imageURL").getValue().toString());
                    uploadsModel.setTitle(snapshot.child("itemName").getValue().toString());
                    uploadsModel.setDescription(snapshot.child("itemDesc").getValue().toString());
                    announcementsList.add(uploadsModel);
                }
                recyclerAdapter = new AnnouncementsAdapter(getContext(), announcementsList);
                recyclerView.setAdapter(recyclerAdapter);
                recyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void clearAll(){
        if (announcementsList != null){
            announcementsList.clear();

            if (recyclerView != null){
//                recyclerAdapter.notifyDataSetChanged();
            }
        }
        announcementsList = new ArrayList<>();
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
            post_fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(), AddLostAndFound.class));
                }
            });
    }
}