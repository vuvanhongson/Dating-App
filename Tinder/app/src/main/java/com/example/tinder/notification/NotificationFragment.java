package com.example.tinder.notification;

import android.app.AppComponentFactory;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tinder.R;
import com.example.tinder.data.model.Result;
import com.example.tinder.home.HomeViewModel;
import com.example.tinder.home.SwipeAdapter;

import java.util.List;

import me.relex.circleindicator.CircleIndicator;

import static com.example.tinder.home.HomeFragment.koloda;


public class NotificationFragment extends AppCompatDialogFragment {

    private TextView tvSkip;
    private RelativeLayout relativeLayoutBottom;
    private ViewPager viewPager;
    private LinearLayout linearLayoutNext;
    private CircleIndicator circleIndicator;
    private ViewPageAdapter viewPageAdapter;

    public NotificationFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notification, container, false);// Inflate the layout for this fragment

        viewPager = root.findViewById(R.id.viewPager);

        tvSkip = root.findViewById(R.id.tvSkip);
        relativeLayoutBottom = root.findViewById(R.id.relativeLayoutBottom);
        linearLayoutNext = root.findViewById(R.id.layoutNext);
        circleIndicator = root.findViewById(R.id.circle_indicartor);

        tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getContext(), "You Touch Me?", Toast.LENGTH_SHORT).show();
                viewPager.setCurrentItem(2);
            }
        });


        linearLayoutNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewPager.getCurrentItem() < 2)
                    viewPager.setCurrentItem( viewPager.getCurrentItem() + 1);
            }
        });
        initIU(root);

        viewPageAdapter = new ViewPageAdapter(getFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        viewPager.setAdapter(viewPageAdapter);

        circleIndicator.setViewPager(viewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == 2)
                {
                    tvSkip.setVisibility(View.GONE);
                    relativeLayoutBottom.setVisibility(View.GONE);
                }
                else
                {
                    tvSkip.setVisibility(View.VISIBLE);
                    relativeLayoutBottom.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        return root;
    }

    public void initIU(View root)
    {

    }


}