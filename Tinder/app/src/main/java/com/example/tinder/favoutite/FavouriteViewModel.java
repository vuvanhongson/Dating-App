package com.example.tinder.favoutite;

import android.content.ClipData;
import android.content.Context;
import android.icu.util.MeasureUnit;

import androidx.cardview.widget.CardView;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import com.example.tinder.Room.AppDatabase;
import com.example.tinder.Room.DAO.itemUserDAO;
import com.example.tinder.Room.ItemUser;

import java.util.ArrayList;
import java.util.List;

public class FavouriteViewModel  extends ViewModel {

    private MutableLiveData<List<ItemUser>> mListUserLiveData;
    private List<ItemUser> mListUser;
    private Context mcontext;

    public FavouriteViewModel(Context context) {
        mListUserLiveData = new MutableLiveData<>();
        this.mcontext = context;
        initData();
    }

    public MutableLiveData<List<ItemUser>> getmListUserLiveData() {
        return mListUserLiveData;
    }


    private void initData() {
        AppDatabase database = Room.databaseBuilder(mcontext, AppDatabase.class, "mydb")
                .allowMainThreadQueries()
                .build();
        itemUserDAO itemDAO = database.getItemDAO();

        List<ItemUser> list = itemDAO.getItems();
        mListUserLiveData.postValue(list);
//
    }
}
