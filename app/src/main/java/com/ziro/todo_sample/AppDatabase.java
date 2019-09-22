package com.ziro.todo_sample;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.ziro.todo_sample.models.ToDo;

@Database(entities = {ToDo.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ToDoDao toDoDao();
}
