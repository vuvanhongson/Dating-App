package com.example.tinder.home;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.tinder.data.model.Result;
import com.example.tinder.data.model.SOProfileResponse;
import com.example.tinder.data.remote.ApiUtils;
import com.example.tinder.data.remote.SOService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {

    private SOService mService;
    private Result result;

    public HomeViewModel(){
        mService = ApiUtils.getInstance().getSOService();
        loadAnswers();
    }

    public void loadAnswers() {
//        String.valueOf(per_page)
        result = new Result();
        mService.getProfile().enqueue(new Callback<SOProfileResponse>() {
            @Override
            public void onResponse(Call<SOProfileResponse> call, Response<SOProfileResponse> response) {

                if(response.isSuccessful()) {
                    result = response.body().getResults().get;

                    Log.d("MainActivity", "posts loaded from API");
                }else {
                    int statusCode  = response.code();
                    // handle request errors depending on status code
                }
            }

            @Override
            public void onFailure(Call<SOProfileResponse> call, Throwable t) {

                Log.d("MainActivity", "error loading from API");

            }
        });
    }

    public Result getProfileResponse() {
        return profileResponse;
    }
}
