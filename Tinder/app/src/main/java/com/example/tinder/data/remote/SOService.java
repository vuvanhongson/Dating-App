package com.example.tinder.data.remote;

import com.example.tinder.data.model.SOProfileResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface SOService {

//    @Headers("app_token: dCuW7UQMbdvpcBDfzolAOSGFIcAec11a")
    @GET("api/0.4/?randomapi")
    Call<SOProfileResponse> getProfile();
}
