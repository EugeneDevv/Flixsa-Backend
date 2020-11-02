package com.eujoh.uoeadminapp.ui.campusbuzz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eujoh.uoeadminapp.R;
import com.eujoh.uoeadminapp.adapters.AnnouncementsAdapter;
import com.eujoh.uoeadminapp.adapters.CampusBuzzAdapter;
import com.eujoh.uoeadminapp.models.UploadsModel;
import com.eujoh.uoeadminapp.ui.announcements.AddAnnouncement;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CampusBuzzFragment extends Fragment {
    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.

    ExtendedFloatingActionButton post_fab;
    RecyclerView recyclerView;

    private DatabaseReference databaseReference;
    private ArrayList<UploadsModel> mUploads;
    private CampusBuzzAdapter recyclerAdapter;
    private Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_campus_buzz, parent, false);

        post_fab = rootView.findViewById(R.id.fab_post);
        recyclerView = rootView.findViewById(R.id.announcement_recycler);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        //Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference("CampusBuzz");

//        ArrayList
        mUploads = new ArrayList<>();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapShot : dataSnapshot.getChildren()){
                    UploadsModel upload = postSnapShot.getValue(UploadsModel.class);
                    upload.setTitle(postSnapShot.child("itemName").getValue().toString());
                    upload.setDescription(postSnapShot.child("itemDesc").getValue().toString());
                    mUploads.add(upload);
                }
                recyclerAdapter = new CampusBuzzAdapter(getActivity().getApplicationContext(), mUploads);
                recyclerView.setAdapter(recyclerAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity().getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        return rootView;
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
                startActivity(new Intent(getActivity(), AddBuzz.class));
            }
        });
    }
}