package com.example.preexamenfinalandroid.home.database.repository;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.preexamenfinalandroid.home.model.Imagen;

import java.util.List;

@Dao
public interface ImageRepository {

    @Insert
    void insert(Imagen foto);

    @Query("Select * from imagen")
    List<Imagen> getAll();

    @Query("Select * from imagen where owner=:owner")
    List<Imagen> getAllByUser(String owner);
}
