package com.example.tinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.tinder.data.model.Result;
import com.example.tinder.data.model.SOProfileResponse;
import com.example.tinder.databinding.ActivityMainBinding;
import com.example.tinder.favoutite.FavouriteFragment;
import com.example.tinder.home.HomeFragment;
import com.example.tinder.home.HomeViewModel;
import com.example.tinder.home.SwipeAdapter;
import com.example.tinder.notification.NotificationFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainViewModel mainViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_baseline_home_24));
        binding.bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.ic_baseline_notifications_24));
        binding.bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_baseline_favorite_24));
        controllayout();

//        //        profile.clear();
//        HomeViewModel homeViewModel = new HomeViewModel();
//        List<Result> profile = homeViewModel.getProfileResponse();
//        Log.d("MainActivity8", "Length Pro: " + profile.size());
//        Log.e("abc", homeViewModel.getProfileResponse().toString());

    }

    private void controllayout()
    {
        binding.bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment frament = null;

                //check condition
                switch (item.getId())
                {
                    case 1:
                        //when id is 1
                        //Init Home Frament
                        frament = new HomeFragment();
                        break;
                    case 2:
                        //when id is 2
                        //Init Notification Frament
                        frament = new NotificationFragment();
                        break;
                    case 3:
                        //when id is 1\3
                        //Init Favourite Frament
                        frament = new FavouriteFragment();
                        break;
                }

                //load frament
                loadFrament(frament);

            }
        });
        binding.bottomNavigation.setCount(2, "10");
        binding.bottomNavigation.show(2, true);

        binding.bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
//                Toast.makeText(MainActivity.this, "You click is " + item.getId(), Toast.LENGTH_SHORT).show();
            }
        });

        binding.bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                //Display toast
//                Toast.makeText(getApplicationContext(), "You Reselected" + item.getId(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    private  void loadFrament (Fragment fragment){
        //replace Frament
        getSupportFragmentManager()
        .beginTransaction()
        .replace(R.id.frame_layout, fragment)
        .commit();
    }



}