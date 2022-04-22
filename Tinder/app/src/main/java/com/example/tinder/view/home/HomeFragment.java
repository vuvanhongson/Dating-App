package com.example.tinder.view.home;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.room.Room;

import com.example.tinder.MainActivity;
import com.example.tinder.Room.AppDatabase;
import com.example.tinder.Room.DAO.itemUserDAO;
import com.example.tinder.Room.ItemUser;
import com.example.tinder.data.remote.SOService;
import com.example.tinder.model.Result;
import com.example.tinder.model.SOProfileResponse;
import com.example.tinder.model.User;
import com.example.tinder.databinding.FragmentHomeBinding;
import com.example.tinder.viewmodel.home.HomeViewModel;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.github.ybq.android.spinkit.style.WanderingCubes;
import com.yalantis.library.Koloda;
import com.yalantis.library.KolodaListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class HomeFragment extends Fragment{
    private static FragmentHomeBinding binding;
    private static SwipeAdapter adapter;

    List<Result> profile = new ArrayList<>();
    public static Koloda koloda;
    public static int point;
    private HomeViewModel homeViewModel;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) { }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding =  FragmentHomeBinding.inflate(inflater,container, false);
        Log.d("MainActivity1", "onCreateView ");
        setIsLoading(true);
        initSwipe();
        return binding.getRoot();
    }

    public void loadAPIData()
    {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        homeViewModel.getResult().observe(getViewLifecycleOwner(), new Observer<Result>() {
            @Override
            public void onChanged(Result result) {
                if(result != null)
                {
                    profile.add(result);
//                    adapter.updateAnswers(result);
                    Log.d("profile", "Profile of: " + profile.toString());
                    Log.d("profile2", "Profile size : " + profile.size());

                }
                else
                {
                    Log.d("profile", "Profile is NULL");
                    Toast.makeText(getContext(), "Error in fetching data", Toast.LENGTH_SHORT).show();
                }
                adapter = new SwipeAdapter(getContext(), profile);
                binding.koloda.setAdapter(adapter);
            }
        });
//        homeViewModel.makeApiCall();
    }

    private void initSwipe()
    {
        loadAPIData();
        loadAPIData();
        loadAPIData();
        Log.d("profile3", "Profile size : " + profile.size());
//        adapter = new SwipeAdapter(getContext(), profile);
//        binding.koloda.setAdapter(adapter);
    }





    private void setIsLoading(boolean isLoading)
    {
        binding.progress.setIndeterminateDrawable( new DoubleBounce());
        binding.progress.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                binding.progress.setVisibility(View.GONE);
//                relativeLayout.setVisibility(View.GONE);
            }
        },2000);
    }

    private void loadProfile() {
//            homeViewModel.getLiveData().observe(getActivity(), new Observer<Result>() {
//                @Override
//                public void onChanged(Result result) {
//                    Log.e("list", result.toString());
//                    profile.add(result);
//                    Log.d("profile11", profile.toString());
////                    adapter.updateAnswers(profile);
//                    if(profile.size() > 3) {
//                        Log.d("profile111"," " + profile.size());
//                        adapter = new SwipeAdapter(getContext(), profile);
//                        binding.koloda.setAdapter(adapter);
//                    }
//                }
//            });
//        Log.d("profile00", profile.toString());

    }

    public static void FlingAdapter(int i) {
        if(i == 1) {
            binding.koloda.onClickLeft();
            point = SwipeAdapter.getPosition();
        }
        else
//            koloda.onClickRight();
        binding.koloda.onButtonClick(true);
        point = SwipeAdapter.getPosition();
    }

    private void initializeDeck() {

        binding.koloda.setKolodaListener(new KolodaListener() {
            @Override
            public void onNewTopCard(int i) {

            }

            @Override
            public void onCardDrag(int i, @NotNull View view, float v) {

            }

            @Override
            public void onCardSwipedLeft(int i) {
//                Toast.makeText(getContext(), "on onCardSwipedLeft", Toast.LENGTH_SHORT).show();
                point = SwipeAdapter.getPosition();
            }

            @Override
            public void onCardSwipedRight(int i) {
                Result result = SwipeAdapter.SwipeRight();
//                Toast.makeText(getContext(), "Swipe Right is : "+ result.getUser().getName().getFirst(), Toast.LENGTH_SHORT).show();
                ItemUser item = new ItemUser();
                AppDatabase database = Room.databaseBuilder(getContext(), AppDatabase.class, "mydb")
                        .allowMainThreadQueries()
                        .build();
                itemUserDAO itemDAO = database.getItemDAO();

                User user = result.getUser();

                item.setGender(user.getGender());
                item.setStreet(user.getLocation().getStreet());
                item.setCity(user.getLocation().getCity());
                item.setState(user.getLocation().getState());
                item.setZip(user.getLocation().getZip());
                item.setTitle(user.getName().getTitle());
                item.setFirst(user.getName().getFirst());
                item.setLast(user.getName().getLast());
                item.setEmail(user.getEmail());
                item.setUsername(user.getUsername());
                item.setPassword(user.getPassword());
                item.setSalt(user.getSalt());
                item.setMd5(user.getMd5());
                item.setSha1(user.getSha1());
                item.setSha256(user.getSha256());
                item.setRegistered(user.getRegistered());
                item.setDob(user.getDob());
                item.setPhone(user.getPhone());
                item.setCell(user.getCell());
                item.setSsn(user.getSsn());
                item.setPicture(user.getPicture());

                itemDAO.insert(item);
                point = SwipeAdapter.getPosition();
            }

            @Override
            public void onClickRight(int i) {

            }

            @Override
            public void onClickLeft(int i) {

            }

            @Override
            public void onCardSingleTap(int i) {

            }

            @Override
            public void onCardDoubleTap(int i) {

            }

            @Override
            public void onCardLongPress(int i) {

            }

            @Override
            public void onEmptyDeck() {

            }
        });

    }

}