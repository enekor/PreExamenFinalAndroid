package com.example.preexamenfinalandroid.home.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "owner")
public class Owner {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String name;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
