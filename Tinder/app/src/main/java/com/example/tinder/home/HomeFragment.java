package com.example.tinder.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import com.example.tinder.R;
import com.example.tinder.Room.AppDatabase;
import com.example.tinder.Room.DAO.itemUserDAO;
import com.example.tinder.Room.ItemUser;
import com.example.tinder.data.model.Result;
import com.example.tinder.data.model.User;
import com.yalantis.library.Koloda;
import com.yalantis.library.KolodaListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    //    private HomeViewModel homeViewModel;
    private static SwipeAdapter adapter;
    Result p;
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
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        koloda = root.findViewById(R.id.koloda);

//        binding = DataBindingUtil.setContentView(getActivity(), R.layout.fragment_home);
//
//        list = new ArrayList<>();
//        adapter = new SwipeAdapter(getContext(), list);
//        koloda.setAdapter(adapter);
        Log.d("MainActivity1", "onCreateView ");
        profile.clear();
        loadProfile();
        initializeDeck();
        return root;
    }


    private void loadProfile() {

        if(point >= 0)
        {
            HomeViewModel homeViewModel = new HomeViewModel();
            for(int i = 0; i <= point; i++)
            {
                homeViewModel.removeList(0);
            }

        }
//        profile.clear();
        HomeViewModel homeViewModel = new HomeViewModel();
        profile = homeViewModel.getProfileResponse();
        Log.d("MainActivity8", "Length Pro: " + profile.size());
        Log.e("abc", homeViewModel.getProfileResponse().toString());


        if(profile.size() > 0)
        {
            adapter = new SwipeAdapter(getContext(), profile);
            koloda.setAdapter(adapter);
        }





    }

    public static void FlingAdapter(int i) {
        if(i == 1) {
            koloda.onClickLeft();
            point = SwipeAdapter.getPosition();
        }
        else
//            koloda.onClickRight();
        koloda.onButtonClick(true);
        point = SwipeAdapter.getPosition();
    }

    private void initializeDeck() {

        koloda.setKolodaListener(new KolodaListener() {
            @Override
            public void onNewTopCard(int i) {

            }

            @Override
            public void onCardDrag(int i, @NotNull View view, float v) {

            }

            @Override
            public void onCardSwipedLeft(int i) {

                Toast.makeText(getContext(), "on onCardSwipedLeft", Toast.LENGTH_SHORT).show();
                point = SwipeAdapter.getPosition();
            }

            @Override
            public void onCardSwipedRight(int i) {
                Result result = SwipeAdapter.SwipeRight();
                Toast.makeText(getContext(), "Swipe Right is : "+ result.getUser().getName().getFirst(), Toast.LENGTH_SHORT).show();
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