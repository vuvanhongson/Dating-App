package com.example.tinder.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tinder.R;
import com.example.tinder.data.model.SOProfileResponse;

import java.util.List;

public class SwipeAdapter extends BaseAdapter {

    private Context context;
    private List<SOProfileResponse> list;

    public SwipeAdapter(Context context, List<SOProfileResponse> list) {
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
        View view;
        if(convertView == null)
        {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_swipe_card, parent, false);
        }
        else
        {
            view = convertView;
        }
        TextView tvten = view.findViewById(R.id.tvten);

        SOProfileResponse p = list.get(position);
        tvten.setText(p.bo);



        return view;
    }
}
