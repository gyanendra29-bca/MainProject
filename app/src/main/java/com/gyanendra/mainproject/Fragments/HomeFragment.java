package com.gyanendra.mainproject.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.gyanendra.mainproject.Adapter.Adapter;
import com.gyanendra.mainproject.FindRoot;
import com.gyanendra.mainproject.Model.Model;
import com.gyanendra.mainproject.R;

public class HomeFragment extends Fragment {


    Button searchRideButton;
    EditText sourceAddressEditText, destinationAddressEditText;
    Adapter adapter;
    RecyclerView recyclerView;




    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ImageView imgview1;

    public HomeFragment() {


        // Required empty public constructor
    }


    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //Assigning the Address of the android components to Perform appropriate action
        searchRideButton = (Button) view.findViewById(R.id.SearchRideBtn);

        sourceAddressEditText = (EditText) view.findViewById(R.id.SearchSourceEdit);
        destinationAddressEditText = (EditText) view.findViewById(R.id.SearchDestinationEdit);
        imgview1=(ImageView) view.findViewById(R.id.imageView1);

        //Recycler view to shows the Rides
        recyclerView = (RecyclerView) view.findViewById(R.id.RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));




        //Implementation of onClickListener to find the Rides
        searchRideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //Getting the String from the EditText
                String sourceAddress = sourceAddressEditText.getText().toString().trim().toLowerCase();
                String destinationAddress = destinationAddressEditText.getText().toString().trim().toLowerCase();

                //Adding the source address and Destination Address to make search easy
                String sourceAndDestinatinAddr = sourceAddress + destinationAddress;

                //FirebaseRecyclerOptions to Populate the data form firebase
                FirebaseRecyclerOptions<Model> options =
                        new FirebaseRecyclerOptions.Builder<Model>()
                                .setQuery(FirebaseDatabase.getInstance().getReference().child("Rides")
                                                .orderByChild("sourceAddrAndDestinationAddr").equalTo(sourceAndDestinatinAddr)
                                        , Model.class)
                                .build();

                adapter = new Adapter(options);
                //Starting the Adapter To listen for Data
                adapter.startListening();
                //Setting the Adapter to RecyclerView
                recyclerView.setAdapter(adapter);
            }
        });
        imgview1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), FindRoot.class);
                startActivity(intent);
            }
        });

        return view;

    }


}