package com.example.tinder.view.home;

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
import com.example.tinder.R;
import com.example.tinder.Room.AppDatabase;
import com.example.tinder.Room.DAO.itemUserDAO;
import com.example.tinder.Room.ItemUser;
import com.example.tinder.model.Result;
import com.example.tinder.model.User;

import java.util.ArrayList;
import java.util.List;

public class SwipeAdapter extends BaseAdapter {

    private static int number;
    private Context context;
//    private static List<FeedHomeViewModel> list;
    ItemUser item = new ItemUser();
    private static List<Result> list = new ArrayList<>();
//    private MutableLiveData<Result> liveData;



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

            tvten.setText(list.get(position).getUser().getLocation().getStreet() + ", " + list.get(position).getUser().getLocation().getCity());
            Glide.with(context)
                    .load( url)
//                    .placeholder(R.drawable.bg_xanhla_01)
                    .into(imgUser);

        } catch (Exception e) {
        }

//        CheckList(position, list);

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
//                tvTitle.setText(R.string.myemailis);
//                tvten.setText(list.get(position).getUser().getEmail());
                tvTitle.setText(R.string.mybirthdayis);
                tvten.setText(list.get(position).getUser().getGender());
                CustomGrayBtn(btnCalendar, btnMagicBall, btnMap, btnPhone, btnLock);
                btnCalendar.setImageResource(R.drawable.calendar_green);
                imgCrollBar.animate().translationXBy(imgCrollBar.getX()).translationX(-(btnMagicBall.getX() + btnMagicBall.getWidth()/2 + 1)).setDuration(200);
            }
        });

        btnMagicBall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvTitle.setText(R.string.mynameis);
                tvten.setText(list.get(position).getUser().getName().getFirst());
                CustomGrayBtn(btnCalendar, btnMagicBall, btnMap, btnPhone, btnLock);
                btnMagicBall.setImageResource(R.drawable.margic_ball_green);
                imgCrollBar.animate().translationXBy(imgCrollBar.getX()).translationX(-(btnCalendar.getX() + btnCalendar.getWidth()/2 + 1)).setDuration(200);
            }
        });
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvTitle.setText(R.string.myaddressis);
                tvten.setText(list.get(position).getUser().getLocation().getStreet() + ", " + list.get(position).getUser().getLocation().getCity());
                CustomGrayBtn(btnCalendar, btnMagicBall, btnMap, btnPhone, btnLock);
                btnMap.setImageResource(R.drawable.mao_green);
                imgCrollBar.animate().translationXBy(imgCrollBar.getX()).translationX(0).setDuration(200);
            }
        });
        btnPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvTitle.setText(R.string.myphonenumberis);
                tvten.setText(list.get(position).getUser().getPhone());
                CustomGrayBtn(btnCalendar, btnMagicBall, btnMap, btnPhone, btnLock);
                btnPhone.setImageResource(R.drawable.phone_green);
                imgCrollBar.animate().translationXBy(imgCrollBar.getX()).translationX((btnMagicBall.getX() + btnMagicBall.getWidth()/2 + 1)).setDuration(200);
            }
        });
        btnLock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvTitle.setText(R.string.myaccountis);
                tvten.setText("@" + list.get(position).getUser().getUsername());
                CustomGrayBtn(btnCalendar, btnMagicBall, btnMap, btnPhone, btnLock);
                btnLock.setImageResource(R.drawable.lock_green);
                imgCrollBar.animate().translationXBy(imgCrollBar.getX()).translationX((btnCalendar.getX() + btnMagicBall.getWidth()/2 + 1)).setDuration(200);
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

    public static Result SwipeRight()
    {
        int point = number;
        return  list.get(point);
    }

    public static int getPosition()
    {
        int point = number;
        return  point;
    }

    public int removeList(int i)
    {
        list.remove(i);
        return 1;
    }

    public void updateAnswers(Result zlist) {
        list.add(zlist);
        notifyDataSetChanged();
    }

    public void setItems(List<Result> items)
    {
        if (items == null)
        {
            return;
        }

        this.list = new ArrayList<>(items);
        notifyDataSetChanged();
    }


}
