package com.example.miskaa.Network;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.miskaa.PojoClasses.ImageRegistration;
import java.util.List;

@Dao
public interface CountryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<ImageRegistration>Country);

    @Query("DELETE FROM Country")
    void deleteAll();

    @Query("SELECT * FROM Country ORDER BY database_id ASC")
    DataSource.Factory<Integer,ImageRegistration>getAllCountry();
}