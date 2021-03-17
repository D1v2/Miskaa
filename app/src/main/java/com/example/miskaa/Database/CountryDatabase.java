package com.example.miskaa.Database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.miskaa.Network.CountryDao;
import com.example.miskaa.PojoClasses.ImageRegistration;

@Database(entities = {ImageRegistration.class},version = 1)
public abstract class CountryDatabase extends RoomDatabase {
    private static final String DATABASE_NAME="Country_database";

    public abstract CountryDao countryDao();

    private static volatile CountryDatabase INSTANCE=null;

    public static CountryDatabase getINSTANCE(Context context){
        if (INSTANCE==null){
            synchronized (CountryDatabase.class){
                if (INSTANCE==null){
                    INSTANCE= Room.databaseBuilder(context,CountryDatabase.class,DATABASE_NAME).addCallback(callback).build();
                }
            }
        }
        return INSTANCE;
    }
    static Callback callback=new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateAsyncTask(INSTANCE).execute();
        }
    };
    static class PopulateAsyncTask extends AsyncTask<Void,Void,Void>{

        private CountryDao  countryDao;

        PopulateAsyncTask(CountryDatabase countryDatabase){
            countryDao=countryDatabase.countryDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            countryDao.deleteAll();
            return null;
        }
    }
}
