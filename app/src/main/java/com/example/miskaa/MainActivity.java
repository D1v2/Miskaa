package com.example.miskaa;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miskaa.Database.CountryRepository;
import com.example.miskaa.Network.RetrofitClient;
import com.example.miskaa.PojoClasses.ImageRegistration;
import com.example.miskaa.ViewModel.CountryAdpater;
import com.example.miskaa.ViewModel.CountryViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MiskaaAdapter miskaaAdapter;
    private CountryAdpater countryAdpater;
    private CountryViewModel countryViewModel;
    private static final String Base="https://restcountries.eu/rest/v2/region/";
    private CountryRepository countryRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       recyclerView=findViewById(R.id.recyclerView);
       countryRepository=new CountryRepository(getApplication());
       recyclerView.setLayoutManager(new LinearLayoutManager(this));
       recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
       miskaaAdapter=new MiskaaAdapter();
       // countryViewModel=new ViewModelProvider(this).get(CountryViewModel.class);
        countryViewModel=new CountryViewModel(getApplication());
        countryViewModel.pagedListLiveData.observe(this, imageRegistrations -> {
                    countryAdpater.submitList(imageRegistrations);
                    recyclerView.setAdapter(countryAdpater);
                });

           getAllCountry();
    }

    public void getData(){
        Call<List<ImageRegistration>> call= RetrofitClient.getApi().getData();
        call.enqueue(new Callback<List<ImageRegistration>>() {
            @Override
            public void onResponse(Call<List<ImageRegistration>> call, Response<List<ImageRegistration>> response) {
                if(response.isSuccessful()) {
                    List<ImageRegistration> imageRegistrations = response.body();
                    miskaaAdapter.setData(imageRegistrations);
                    recyclerView.setAdapter(miskaaAdapter);

                }
            }

            @Override
            public void onFailure(Call<List<ImageRegistration>> call, Throwable t) {
                Log.d("Fail Response",t.getMessage());
            }
        });
    }
    private void getAllCountry(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Base)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api=retrofit.create(Api.class);
        Call<List<ImageRegistration>> call= api.getData();
        call.enqueue(new Callback<List<ImageRegistration>>() {
            @Override
            public void onResponse(Call<List<ImageRegistration>> call, Response<List<ImageRegistration>> response) {
                if(response.isSuccessful()) {
                    List<ImageRegistration> imageRegistrations = response.body();
                    countryAdpater=new CountryAdpater(getApplicationContext(),imageRegistrations);
                    countryRepository.insert(response.body());
                     miskaaAdapter.setData(imageRegistrations);
                     recyclerView.setAdapter(miskaaAdapter);

                }
            }

            @Override
            public void onFailure(Call<List<ImageRegistration>> call, Throwable t) {
                Log.d("Fail Response",t.getMessage());
            }
        });
    }
}