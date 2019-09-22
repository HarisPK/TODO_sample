package com.ziro.todo_sample;

import androidx.room.Room;
import android.content.Context;

public class DataBaseClient {

    private Context mCtx;
    private static DataBaseClient mInstance;

    //our app database object
    private AppDatabase appDatabase;

    private DataBaseClient(Context mCtx) {
        this.mCtx = mCtx;

        //creating the app database with Room database builder
        //MyToDos is the name of the database
        appDatabase = Room.databaseBuilder(mCtx, AppDatabase.class, "MyToDos").allowMainThreadQueries().build();
    }

    public static synchronized DataBaseClient getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new DataBaseClient(mCtx);
        }
        return mInstance;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
