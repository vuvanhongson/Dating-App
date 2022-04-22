package com.example.tinder.view.favoutite;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tinder.Room.ItemUser;
import com.example.tinder.databinding.FragmentFavouriteBinding;
import com.example.tinder.viewmodel.favourite.FavouriteViewModel;

import java.util.ArrayList;
import java.util.List;


public class FavouriteFragment extends Fragment {

    private FragmentFavouriteBinding  binding;
    private itemUserAdapter mAdapter;
    private FavouriteViewModel favouriteViewModel;
//    private RecyclerView mRecyclerView;
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
      binding =  FragmentFavouriteBinding.inflate(inflater,container, false);
        Log.d("MainActivity3", "onCreateView ");
//      viewmodel
        favouriteViewModel = new FavouriteViewModel(this.getContext());
        mAdapter = new itemUserAdapter(getContext(), new ArrayList<ItemUser>(0), new itemUserAdapter.PostItemListener() {

            @Override
            public void onPostClick(String id) {

            }
        });

        //        RecyclerView.LayoutManager
        manager = new LinearLayoutManager(getContext());
        binding.rcFilm.setLayoutManager(manager);
        binding.rcFilm.setAdapter(mAdapter);
        binding.rcFilm.setHasFixedSize(true);

        favouriteViewModel.getmListUserLiveData().observe(getActivity(), new Observer<List<ItemUser>>() {
            @Override
            public void onChanged(List<ItemUser> itemUsers) {
                mAdapter.updateAnswers(itemUsers);

            }
        });



        return binding.getRoot();
    }

}