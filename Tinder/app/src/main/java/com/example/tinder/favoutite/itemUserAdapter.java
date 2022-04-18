package com.example.tinder.favoutite;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.bumptech.glide.Glide;
import com.example.tinder.R;
import com.example.tinder.Room.AppDatabase;
import com.example.tinder.Room.DAO.itemUserDAO;
import com.example.tinder.Room.ItemUser;
import com.squareup.picasso.Picasso;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class itemUserAdapter extends RecyclerView.Adapter<itemUserAdapter.ViewHolder> {


    private List<ItemUser> mItems;
    private Context mContext;
    private PostItemListener mItemListener;

    public itemUserAdapter(Context context, List<ItemUser> posts, PostItemListener itemListener) {
        mItems = posts;
        mContext = context;
        mItemListener = itemListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

//            View postView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
        View postView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_1dong_favourite, parent, false);

        ViewHolder viewHolder = new ViewHolder(postView, this.mItemListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemUser item = mItems.get(position);

        String url = item.getPicture();
        ImageView imageView = holder.imgAvatar;
        Glide.with(mContext)
                .load( url)
//                    .placeholder(R.drawable.bg_xanhla_01)
                .into(imageView);


        TextView tvTen = holder.tvName;
        tvTen.setText(item.getFirst());

        TextView tvUN = holder.tvUserName;
        tvUN.setText(item.getUsername());


        TextView tvphone = holder.tvPhone;
        tvphone.setText(item.getPhone());

        CardView layoutItem = holder.layoutItem;
        ConstraintLayout layoutConstrain = holder.layoutConstrain;

        ImageView btnRemove = holder.btnRemove;
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext, android.R.style.Theme_DeviceDefault_Light_Dialog);
                builder.setTitle(R.string.deleteItemUser);
                builder.setMessage(R.string.luachondexoa);
                builder.setIcon(R.drawable.ic_baseline_remove_circle_outline_24);
                builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mItems.remove(position);
                        notifyDataSetChanged();
                        AppDatabase database = Room.databaseBuilder(mContext, AppDatabase.class, "mydb")
                                .allowMainThreadQueries()
                                .build();
                        itemUserDAO itemDAO = database.getItemDAO();
                        itemDAO.deleteItemById(item.getId());

                    }
                });
                builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView tvName, tvUserName, tvPhone;
        public ImageView imgAvatar, btnRemove;
        public CardView layoutItem;
        public ConstraintLayout layoutConstrain;
        PostItemListener mItemListener;

        public ViewHolder(View itemView, PostItemListener postItemListener) {
            super(itemView);

            // Anh xa
            tvName = itemView.findViewById(R.id.tvName);
            tvUserName = itemView.findViewById(R.id.tvUserName);
            tvPhone = itemView.findViewById(R.id.tvPhone);
            imgAvatar = itemView.findViewById(R.id.imgAvatar);
            btnRemove = itemView.findViewById(R.id.btnRemove);
            layoutItem = itemView.findViewById(R.id.layoutItem);
            layoutConstrain = itemView.findViewById(R.id.layoutConstrain);

            this.mItemListener = postItemListener;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            ItemUser item = getItem(getAdapterPosition());
            this.mItemListener.onPostClick(item.getUsername());

            notifyDataSetChanged();
        }




    }

    public void updateAnswers(List<ItemUser> items) {
        mItems = items;
        notifyDataSetChanged();
    }

    private ItemUser getItem(int adapterPosition) {
        return mItems.get(adapterPosition);
    }

    public interface PostItemListener {
        void onPostClick(String id);
    }
}
