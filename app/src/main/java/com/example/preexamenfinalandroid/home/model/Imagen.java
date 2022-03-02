package com.example.preexamenfinalandroid.home.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "imagen")
public class Imagen {

    @PrimaryKey(autoGenerate = true) private long id;
    private String name;
    private String owner;
    private String image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
