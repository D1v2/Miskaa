package com.example.miskaa.Database;

import android.app.Application;
import android.os.AsyncTask;

import com.example.miskaa.Network.CountryDao;
import com.example.miskaa.PojoClasses.ImageRegistration;

import java.util.List;

public class CountryRepository {

    private CountryDatabase countryDatabase;

    public CountryRepository(Application application){
        countryDatabase=CountryDatabase.getINSTANCE(application);
    }

    public void insert(List<ImageRegistration>imageRegistrationList){
        new InsertAsyncTask(countryDatabase).execute(imageRegistrationList);

    }

    static class InsertAsyncTask extends AsyncTask<List<ImageRegistration>,Void,Void>{

        private CountryDao countryDao;
        private InsertAsyncTask(CountryDatabase countryDatabase){
            countryDao=countryDatabase.countryDao();
        }
        @Override
        protected Void doInBackground(List<ImageRegistration>... lists) {
            countryDao.insert(lists[0]);
            return null;
        }
    }
}
