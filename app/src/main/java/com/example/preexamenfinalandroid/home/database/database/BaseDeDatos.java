package com.example.preexamenfinalandroid.home.database.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.preexamenfinalandroid.home.database.repository.ImageRepository;
import com.example.preexamenfinalandroid.home.database.repository.OwnerRepository;
import com.example.preexamenfinalandroid.home.model.Imagen;
import com.example.preexamenfinalandroid.home.model.Owner;

@Database(entities = {Imagen.class, Owner.class},version=1)
public abstract class BaseDeDatos extends RoomDatabase {

    private static final String NAME = "imagenes";

    public abstract ImageRepository imageRepository();
    public abstract OwnerRepository ownerRepository();

    private static volatile BaseDeDatos instance;

    public synchronized static BaseDeDatos getInstance(final Context contexto){
        if(instance==null){
            instance = Room.databaseBuilder(contexto,
                    BaseDeDatos.class,NAME).allowMainThreadQueries().build();
        }
        return instance;
    }


}
