package com.example.miskaa;

import com.example.miskaa.PojoClasses.ImageRegistration;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    @GET("Asia")
    Call<List<ImageRegistration>> getData(
    );
}