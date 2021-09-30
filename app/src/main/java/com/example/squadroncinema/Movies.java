//package com.example.squadroncinema;
//
//import android.content.Intent;
//import android.os.Bundle;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.ProgressBar;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import com.google.android.material.bottomsheet.BottomSheetDialog;
//import com.google.android.material.floatingactionbutton.FloatingActionButton;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.database.ChildEventListener;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.squareup.picasso.Picasso;
//
//import java.util.ArrayList;
//
///**
// * A simple {@link Fragment} subclass.
// * Use the {@link Movies#newInstance} factory method to
// * create an instance of this fragment.
// */
//public class Movies extends Fragment implements MovieRVAdapter.MovieClickInterface {
//
//     TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public Movies() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment Movies.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static Movies newInstance(String param1, String param2) {
//        Movies fragment = new Movies();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////        if (getArguments() != null) {
////            mParam1 = getArguments().getString(ARG_PARAM1);
////            mParam2 = getArguments().getString(ARG_PARAM2);
////        }
//    }
//
//    private RecyclerView moviesRV;
//    private ProgressBar loadingPB;
////    private FloatingActionButton addFAB;
//
//    //firebase database
//    private FirebaseDatabase firebaseDatabase;
//    private DatabaseReference databaseReference;
//
//    //retrive the list
//    private ArrayList<MovieRVModal> movieRVModalArrayList;
//
//    //card for bottom
//    private RelativeLayout bottomSheetRL;
//
//    //cardidBtnEdit
//    private MovieRVAdapter movieRVAdapter;
//
//    private FirebaseAuth mAuth;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_movies, container, false);
//        // Inflate the layout for this fragment
//
//        moviesRV = view.findViewById(R.id.idRVMovie);
//        bottomSheetRL = view.findViewById(R.id.idRLBSheet);
//        loadingPB = view.findViewById(R.id.idPBLoading);
//        firebaseDatabase = FirebaseDatabase.getInstance();
//        movieRVModalArrayList = new ArrayList<>();
//
//        //inialize firebase db
//        databaseReference = firebaseDatabase.getReference("Movies");
//
//        mAuth = FirebaseAuth.getInstance();
//
//        //add course
////        addFAB.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                startActivity(new Intent(MainActivity.this, AddCourseActivity.class));
////            }
////        });
//
//        movieRVAdapter = new MovieRVAdapter(movieRVModalArrayList, this, this);
//
//        //layout manager
//       moviesRV.setLayoutManager(new LinearLayoutManager(this));
//
//        moviesRV.setAdapter(movieRVAdapter);
//        //get all the courses method call
//        getAllMovies();
//
//        return view;
//    }
//
//    private void getAllMovies() {
//        movieRVModalArrayList.clear();
//        databaseReference.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                loadingPB.setVisibility(View.GONE);
//                movieRVModalArrayList.add(snapshot.getValue(MovieRVModal.class));
//                movieRVAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                loadingPB.setVisibility(View.GONE);
//                movieRVAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
//                loadingPB.setVisibility(View.GONE);
//                movieRVAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                loadingPB.setVisibility(View.GONE);
//                movieRVAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }
//
//    @Override
//    public void onMovieClick(int position) {
//        //call the bottom sheet function
//        displayBottomSheet(movieRVModalArrayList.get(position));
//    }
//
//    //displaying bottom sheet function
//    private void displayBottomSheet(MovieRVModal movieRVModal) {
////        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
//
//
//    }
//}