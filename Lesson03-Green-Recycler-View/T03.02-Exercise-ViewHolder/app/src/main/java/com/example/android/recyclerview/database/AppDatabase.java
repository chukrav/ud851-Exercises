package com.example.android.recyclerview.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.concurrent.Executors;

@Database(entities = {TaskEntry.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static final String LOG_TAG = AppDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "todolist";
    private static AppDatabase sInstance;

    public static AppDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                Log.d(LOG_TAG, "Creating new database instance");
//                sInstance = Room.databaseBuilder(context.getApplicationContext(),
//                        AppDatabase.class, AppDatabase.DATABASE_NAME)
//                        // Queries should be done in a separate thread to avoid locking the UI
//                        // We will allow this ONLY TEMPORALLY to see that our DB is working
//                        .allowMainThreadQueries()
//                        .build();
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class,DATABASE_NAME)
                        .addCallback(new Callback() {
                            @Override
                            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                super.onCreate(db);
                                Executors.newSingleThreadExecutor().execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        Log.d(LOG_TAG,"OnCreate task to do");
                                    }
                                });
                            }
                        })
                        .allowMainThreadQueries()
                        .build();

            }
        }
        Log.d(LOG_TAG, "Getting the database instance");
        return sInstance;
    }

    public abstract TaskDao taskDao();


}
