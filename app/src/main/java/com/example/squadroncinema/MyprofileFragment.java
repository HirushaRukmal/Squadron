package com.example.squadroncinema;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.FirebaseFirestore;

/*
    Author    : Hirusha Rukmal
    Developed : Android Studio Arctic Fox | 2020.3.1 patch 2
    Implnote  : Development of the user profile
*/

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyprofileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyprofileFragment extends Fragment {

    //declaring variables
    private TextView fName, lName, email, telephone;
    private Button delete,update;
    private FirebaseAuth fAuth;
    private DatabaseReference ref;
    private FirebaseFirestore fStore;
    private String userId;
    private FirebaseUser fuser;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MyprofileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyprofileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyprofileFragment newInstance(String param1, String param2) {
        MyprofileFragment fragment = new MyprofileFragment();
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
        return inflater.inflate(R.layout.fragment_myprofile, container, false);

    }


}