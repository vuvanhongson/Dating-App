package com.example.tinder.favoutite;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tinder.R;
import com.example.tinder.Room.AppDatabase;
import com.example.tinder.Room.DAO.itemUserDAO;
import com.example.tinder.Room.ItemUser;
import com.example.tinder.data.model.User;
import com.example.tinder.home.HomeFragment;

import java.util.ArrayList;
import java.util.List;


public class FavouriteFragment extends Fragment {
    private itemUserAdapter mAdapter;
    private RecyclerView mRecyclerView;
    LinearLayoutManager manager;

    public FavouriteFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
        Log.d("MainActivity2", "onCreate ");


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_favourite, container, false);
        // Inflate the layout for this fragment
        Log.d("MainActivity3", "onCreateView ");
        mRecyclerView = (RecyclerView) root.findViewById(R.id.rc_film);
        mAdapter = new itemUserAdapter(getContext(), new ArrayList<ItemUser>(0), new itemUserAdapter.PostItemListener() {

            @Override
            public void onPostClick(String id) {

            }
        });

        //        RecyclerView.LayoutManager
        manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
//        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
//        mRecyclerView.addItemDecoration(itemDecoration);

        loadUser();


        return root;
    }

    private void loadUser() {
        AppDatabase database = Room.databaseBuilder(getContext(), AppDatabase.class, "mydb")
                .allowMainThreadQueries()
                .build();
        itemUserDAO itemDAO = database.getItemDAO();

        List<ItemUser> list = itemDAO.getItems();


        mAdapter.updateAnswers(list);

    }
}