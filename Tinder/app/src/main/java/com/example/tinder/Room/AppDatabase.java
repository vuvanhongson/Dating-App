package com.example.tinder.Room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.tinder.Room.DAO.itemUserDAO;
import com.example.tinder.data.model.User;

@Database(entities = {ItemUser.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract itemUserDAO getItemDAO();
}
