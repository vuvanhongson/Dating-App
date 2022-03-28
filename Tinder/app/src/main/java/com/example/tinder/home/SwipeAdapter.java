package com.example.tinder.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tinder.R;
import com.example.tinder.data.model.Result;
import com.example.tinder.data.model.SOProfileResponse;

import java.util.List;

public class SwipeAdapter extends BaseAdapter {

    private Context context;
    private List<Result> list;


    public SwipeAdapter(Context context, List<Result> list) {
        this.context = context;
        this.list = list;
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

        ImageView btnCalendar, btnMagicBall, btnMap, btnPhone, btnLock, imgCrollBar;
        TextView tvten, tvTitle;

        btnCalendar = convertView.findViewById(R.id.btnCalendar);
        btnMagicBall = convertView.findViewById(R.id.btnMagicBall);
        btnMap = convertView.findViewById(R.id.btnMap);
        btnPhone = convertView.findViewById(R.id.btnPhone);
        btnLock = convertView.findViewById(R.id.btnLock);
        imgCrollBar = convertView.findViewById(R.id.imgScrollBar);
        tvTitle = convertView.findViewById(R.id.tvTitle);
        tvten = convertView.findViewById(R.id.tvten);

        CustomClick( btnCalendar, btnMagicBall, btnMap, btnPhone, btnLock,imgCrollBar, tvten, tvTitle);




        return convertView;
    }

    private void CustomGrayBtn(ImageView btnCalendar,ImageView  btnMagicBall,ImageView  btnMap,ImageView  btnPhone,ImageView  btnLock) {
        btnCalendar.setImageResource(R.drawable.canlendar_grey);
        btnMagicBall.setImageResource(R.drawable.margic_ball_grey);
        btnMap.setImageResource(R.drawable.map_grey);
        btnPhone.setImageResource(R.drawable.phone_grey);
        btnLock.setImageResource(R.drawable.lock_grey);
    }

    private void AnhXa(View convertView, ImageView btnCalendar, ImageView  btnMagicBall,ImageView  btnMap,ImageView  btnPhone,ImageView  btnLock, TextView tvten) {
        btnCalendar = convertView.findViewById(R.id.btnCalendar);
        btnMagicBall = convertView.findViewById(R.id.btnMagicBall);
        btnMap = convertView.findViewById(R.id.btnMap);
        btnPhone = convertView.findViewById(R.id.btnPhone);
        btnLock = convertView.findViewById(R.id.btnLock);

        tvten = convertView.findViewById(R.id.tvten);
    }

    private void CustomClick(ImageView btnCalendar,ImageView  btnMagicBall,ImageView  btnMap,ImageView  btnPhone,ImageView  btnLock,ImageView imgCrollBar, TextView tvten, TextView tvTitle) {
        tvten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvten.setText("Vũ Văn Hồng Sơn");
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
                tvten.setText("Vũ Văn Hồng Sơn");
                CustomGrayBtn(btnCalendar, btnMagicBall, btnMap, btnPhone, btnLock);
                btnMagicBall.setImageResource(R.drawable.margic_ball_green);
                imgCrollBar.animate().translationXBy(imgCrollBar.getX()).translationX(btnMagicBall.).setDuration(200);
            }
        });
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvTitle.setText("My address is");
                tvten.setText("106/3/5 Pham Van Chieu");
                CustomGrayBtn(btnCalendar, btnMagicBall, btnMap, btnPhone, btnLock);
                btnMap.setImageResource(R.drawable.mao_green);
                imgCrollBar.animate().translationXBy(imgCrollBar.getX()).translationX(0).setDuration(200);
            }
        });
        btnPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvTitle.setText("My phone number is");
                tvten.setText("0945 974 xxx");
                CustomGrayBtn(btnCalendar, btnMagicBall, btnMap, btnPhone, btnLock);
                btnPhone.setImageResource(R.drawable.phone_green);
                imgCrollBar.animate().translationXBy(imgCrollBar.getX()).translationX(170).setDuration(200);
            }
        });
        btnLock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvTitle.setText("My account is");
                tvten.setText("@vuvanhongson");
                CustomGrayBtn(btnCalendar, btnMagicBall, btnMap, btnPhone, btnLock);
                btnLock.setImageResource(R.drawable.lock_green);
                imgCrollBar.animate().translationXBy(imgCrollBar.getX()).translationX(350).setDuration(200);
            }
        });
    }




}
