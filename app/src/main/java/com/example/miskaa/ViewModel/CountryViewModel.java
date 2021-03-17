package com.example.miskaa.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.miskaa.Database.CountryDatabase;
import com.example.miskaa.Network.CountryDao;
import com.example.miskaa.PojoClasses.ImageRegistration;

public class CountryViewModel extends AndroidViewModel {
    public final LiveData<PagedList<ImageRegistration>>pagedListLiveData;
    private CountryDao countryDao;
    public CountryViewModel(@NonNull Application application) {
        super(application);
        countryDao= CountryDatabase.getINSTANCE(application).countryDao();
        pagedListLiveData=new LivePagedListBuilder<>(
                countryDao.getAllCountry(),5
        ).build();
    }

}
