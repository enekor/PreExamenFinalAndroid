package com.example.preexamenfinalandroid.home.database.repository;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.preexamenfinalandroid.home.model.Owner;

import java.util.List;

@Dao
public interface OwnerRepository {

    @Insert
    void insert(Owner owner);

    @Query("Select * from owner")
    List<Owner> getAll();
}
