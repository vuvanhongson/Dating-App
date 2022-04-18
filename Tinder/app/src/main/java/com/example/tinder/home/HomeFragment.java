package com.example.tinder.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.room.Room;

import com.example.tinder.R;
import com.example.tinder.Room.AppDatabase;
import com.example.tinder.Room.DAO.itemUserDAO;
import com.example.tinder.Room.ItemUser;
import com.example.tinder.data.model.Result;
import com.example.tinder.data.model.User;
import com.example.tinder.databinding.FragmentFavouriteBinding;
import com.example.tinder.databinding.FragmentHomeBinding;
import com.yalantis.library.Koloda;
import com.yalantis.library.KolodaListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    private static FragmentHomeBinding binding;
//    private FragmentHomeBinding binding;
    private HomeViewModel homeViewModel;
    private static SwipeAdapter adapter;
//    private MutableLiveData<Result> liveData;
//    Result p;
    List<Result> profile = new ArrayList<>();
    public static Koloda koloda;
    public static int point;

    public HomeFragment() {
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
        binding =  FragmentHomeBinding.inflate(inflater,container, false);
        Log.d("MainActivity1", "onCreateView ");
        homeViewModel = new HomeViewModel();
//        profile.clear();
        loadProfile();
        initializeDeck();
        return binding.getRoot();
    }


    private void loadProfile() {
            homeViewModel.getLiveData().observe(getActivity(), new Observer<Result>() {
                @Override
                public void onChanged(Result result) {
                    Log.e("list", result.toString());
                    profile.add(result);
                    Log.d("profile11", profile.toString());
//                    adapter.updateAnswers(profile);
                    if(profile.size() > 3) {
                        Log.d("profile111"," " + profile.size());
                        adapter = new SwipeAdapter(getContext(), profile);
                        binding.koloda.setAdapter(adapter);
                    }
                }
            });
        Log.d("profile00", profile.toString());

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