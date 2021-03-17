package com.example.miskaa.Network;

import com.example.miskaa.PojoClasses.ImageRegistration;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class RetrofitClient {
    private static final String Base_URL="https://restcountries.eu/rest/v2/region/";
    public static Api api=null;

    public static Api getApi(){
        if(api==null){
            Retrofit retrofit=new Retrofit.Builder()
                    .baseUrl(Base_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            api=retrofit.create(Api.class);
        }
        return api;
    }

    public interface Api {
        @GET("Asia")
        Call<List<ImageRegistration>> getData(
        );
    }
}
