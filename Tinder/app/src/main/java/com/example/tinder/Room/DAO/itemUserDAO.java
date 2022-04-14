package com.example.tinder.Room.DAO;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.tinder.Room.ItemUser;
import com.example.tinder.data.model.User;

import java.util.List;

@Dao
    public interface itemUserDAO {
        @Insert
        public void insert(ItemUser... items);
        @Update
        public void update(ItemUser... items);
        @Delete
        public void delete(ItemUser item);

        @Query("SELECT * FROM ItemUser")
        public List<ItemUser> getItems();

        @Query("DELETE FROM ItemUser WHERE id = :id")
        public void deleteItemById(int id);


    }
