package com.example.tinder.home;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.room.Room;

import com.bumptech.glide.Glide;
import com.example.tinder.MainActivity;
import com.example.tinder.R;
import com.example.tinder.Room.AppDatabase;
import com.example.tinder.Room.DAO.itemUserDAO;
import com.example.tinder.Room.ItemUser;
import com.example.tinder.data.model.Result;
import com.example.tinder.data.model.User;
import com.squareup.picasso.Picasso;

import java.net.CookieHandler;
import java.util.ArrayList;
import java.util.List;

public class SwipeAdapter extends BaseAdapter {

    private static int number;
    private Context context;
    private static List<Result> list;
    ItemUser item = new ItemUser();


    public SwipeAdapter(Context context, List<Result> zlist) {
        this.context = context;
        this.list = zlist;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        View view;
        convertView = LayoutInflater.from(context).inflate(R.layout.layout_item_swipe_card, null);

        ImageView btnCalendar, btnMagicBall, btnMap, btnPhone, btnLock, imgCrollBar, imgUser, btnLeft, btnRight;
        TextView tvten, tvTitle;

        btnCalendar = convertView.findViewById(R.id.btnCalendar);
        btnMagicBall = convertView.findViewById(R.id.btnMagicBall);
        btnMap = convertView.findViewById(R.id.btnMap);
        btnPhone = convertView.findViewById(R.id.btnPhone);
        btnLock = convertView.findViewById(R.id.btnLock);
        imgCrollBar = convertView.findViewById(R.id.imgScrollBar);
        imgUser = convertView.findViewById(R.id.imgUser);
        tvTitle = convertView.findViewById(R.id.tvTitle);
        tvten = convertView.findViewById(R.id.tvten);
        btnLeft = convertView.findViewById(R.id.btnLeft);
        btnRight = convertView.findViewById(R.id.btnRight);

        CustomClick(btnCalendar, btnMagicBall, btnMap, btnPhone, btnLock, imgCrollBar, tvten, tvTitle, position, btnLeft, btnRight);


        try {
            String url = list.get(position).getUser().getPicture();
            Log.d("MainActivity999", "Position is profile of: " + position);
            Log.d("MainActivity999", "This is profile of: " + list.get(position).getUser().getName().getFirst() + " - position " + position +" - " + url);

//            Picasso.with(context)
//                    .load("http://api.randomuser.me/portraits/men/86.jpg")
//                    .placeholder(R.mipmap.ic_launcher)
////                    .error(R.drawable.btn_incorress)
//                    .resize(600,600)
//                    .into(imgUser);

            tvten.setText(list.get(position).getUser().getLocation().getStreet() + ", " + list.get(position).getUser().getLocation().getCity());
            Glide.with(context)
                    .load( url)
//                    .placeholder(R.drawable.bg_xanhla_01)
                    .into(imgUser);

        } catch (Exception e) {
        }

        CheckList(position);

        number = position;


        return convertView;
    }

    private void CustomGrayBtn(ImageView btnCalendar, ImageView btnMagicBall, ImageView btnMap, ImageView btnPhone, ImageView btnLock) {
        btnCalendar.setImageResource(R.drawable.canlendar_grey);
        btnMagicBall.setImageResource(R.drawable.margic_ball_grey);
        btnMap.setImageResource(R.drawable.map_grey);
        btnPhone.setImageResource(R.drawable.phone_grey);
        btnLock.setImageResource(R.drawable.lock_grey);
    }

    private void AnhXa(View convertView, ImageView btnCalendar, ImageView btnMagicBall, ImageView btnMap, ImageView btnPhone, ImageView btnLock, TextView tvten) {
        btnCalendar = convertView.findViewById(R.id.btnCalendar);
        btnMagicBall = convertView.findViewById(R.id.btnMagicBall);
        btnMap = convertView.findViewById(R.id.btnMap);
        btnPhone = convertView.findViewById(R.id.btnPhone);
        btnLock = convertView.findViewById(R.id.btnLock);

        tvten = convertView.findViewById(R.id.tvten);
    }

    private void CustomClick(ImageView btnCalendar, ImageView btnMagicBall, ImageView btnMap, ImageView btnPhone, ImageView btnLock, ImageView imgCrollBar, TextView tvten, TextView tvTitle, int position, ImageView btnLeft,ImageView btnRight) {
        tvten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "This is Position" + position, Toast.LENGTH_SHORT).show();
            }
        });
        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                tvTitle.setText("My birthday is");
                tvten.setText("17/02/2000");
                CustomGrayBtn(btnCalendar, btnMagicBall, btnMap, btnPhone, btnLock);
                btnCalendar.setImageResource(R.drawable.calendar_green);
                imgCrollBar.animate().translationXBy(imgCrollBar.getX()).translationX(-170).setDuration(200);
            }
        });

        btnMagicBall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvTitle.setText("My name is");
                tvten.setText(list.get(position).getUser().getName().getFirst());
                CustomGrayBtn(btnCalendar, btnMagicBall, btnMap, btnPhone, btnLock);
                btnMagicBall.setImageResource(R.drawable.margic_ball_green);
                imgCrollBar.animate().translationXBy(imgCrollBar.getX()).translationX(-350).setDuration(200);
            }
        });
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvTitle.setText("My address is");
                tvten.setText(list.get(position).getUser().getLocation().getStreet() + ", " + list.get(position).getUser().getLocation().getCity());
                CustomGrayBtn(btnCalendar, btnMagicBall, btnMap, btnPhone, btnLock);
                btnMap.setImageResource(R.drawable.mao_green);
                imgCrollBar.animate().translationXBy(imgCrollBar.getX()).translationX(0).setDuration(200);
            }
        });
        btnPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvTitle.setText("My phone number is");
                tvten.setText(list.get(position).getUser().getPhone());
                CustomGrayBtn(btnCalendar, btnMagicBall, btnMap, btnPhone, btnLock);
                btnPhone.setImageResource(R.drawable.phone_green);
                imgCrollBar.animate().translationXBy(imgCrollBar.getX()).translationX(170).setDuration(200);
            }
        });
        btnLock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvTitle.setText("My account is");
                tvten.setText("@" + list.get(position).getUser().getUsername());
                CustomGrayBtn(btnCalendar, btnMagicBall, btnMap, btnPhone, btnLock);
                btnLock.setImageResource(R.drawable.lock_green);
                imgCrollBar.animate().translationXBy(imgCrollBar.getX()).translationX(350).setDuration(200);
            }
        });
        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeFragment.FlingAdapter(1);
            }
        });
        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AppDatabase database = Room.databaseBuilder(context, AppDatabase.class, "mydb")
                        .allowMainThreadQueries()
                        .build();
                itemUserDAO itemDAO = database.getItemDAO();

                User user = list.get(position).getUser();

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
                HomeFragment.FlingAdapter(0);
            }
        });
    }

    private void CheckList(int position)
    {
        if((list.size() - 3) >= position)
        {
            HomeViewModel homeViewModel = new HomeViewModel();


            List<Result> profile = homeViewModel.getProfileResponse();
            Log.e("size", String.valueOf(profile.size()));

            this.list = profile;

        }
    }

    public static Result SwipeRight()
    {
        int point = number - 3;

        return  list.get(point);
    }

    public static int getPosition()
    {
        int point = number - 3;

        return  point;
    }

    public int removeList(int i)
    {
        list.remove(i);
        return 1;
    }


}
