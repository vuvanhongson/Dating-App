package com.example.tinder.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tinder.MainActivity;
import com.example.tinder.R;
import com.example.tinder.data.model.Result;
import com.example.tinder.data.model.SOProfileResponse;
import com.example.tinder.databinding.ActivityMainBinding;
import com.example.tinder.databinding.FragmentHomeBinding;
import com.yalantis.library.Koloda;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private HomeViewModel homeViewModel;
    private SwipeAdapter adapter;
    private List<Integer> list;
    List<Result> pro = new ArrayList<>();
    Koloda koloda;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)  {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        koloda = root.findViewById(R.id.koloda);

//        binding = DataBindingUtil.setContentView(getActivity(), R.layout.fragment_home);
//
//        list = new ArrayList<>();
//        adapter = new SwipeAdapter(getContext(), list);
//        koloda.setAdapter(adapter);
        Log.d("MainActivity", "onCreateView ");
        loadProfile();
        return  root;
    }

    private void loadProfile()
    {
        homeViewModel = new HomeViewModel();
        for(int i = 0; i <= 2; i++)
        {
            Log.d("MainActivity", "Load i:" + i);
            Result p = new Result();
            p = homeViewModel.getProfileResponse();
            pro.add(p);
        }


        adapter = new SwipeAdapter(getActivity(), pro);
        koloda.setAdapter(adapter);

    }


}