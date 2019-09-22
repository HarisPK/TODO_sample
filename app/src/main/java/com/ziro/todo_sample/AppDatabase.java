package com.ziro.todo_sample;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.ziro.todo_sample.models.ToDo;

@Database(entities = {ToDo.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ToDoDao toDoDao();
}
