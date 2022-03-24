package com.example.tinder.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tinder.MainActivity;
import com.example.tinder.R;
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
    List<SOProfileResponse> pro = new ArrayList<>();
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

        return  root;
    }

    private void loadProfile()
    {
        for(int i = 1; i > 20; i++)
        {
            SOProfileResponse p = new SOProfileResponse();
            p = homeViewModel.getProfileResponse();
            pro.add(p);
            adapter = new SwipeAdapter(getContext(), pro);
            koloda.setAdapter(adapter);
        }

    }


}