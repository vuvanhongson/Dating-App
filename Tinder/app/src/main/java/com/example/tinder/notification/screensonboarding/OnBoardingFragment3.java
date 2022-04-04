package com.example.tinder.notification.screensonboarding;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.tinder.R;
import com.example.tinder.notification.NotificationFragment;


public class OnBoardingFragment3 extends Fragment {
    private Button btgetStart;
    private  View mview;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mview = inflater.inflate(R.layout.fragment_on_boarding3, container, false);

        btgetStart = mview.findViewById(R.id.btStart);

        btgetStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment frament = new NotificationFragment();
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame_layout, frament)
                        .commit();
            }
        });

        return  mview;
    }
}