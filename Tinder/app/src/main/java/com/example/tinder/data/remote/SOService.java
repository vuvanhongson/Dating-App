package com.example.tinder.data.remote;

import com.example.tinder.model.Result;
import com.example.tinder.model.SOProfileResponse;

import retrofit2.http.GET;
import rx.Observable;
import rx.Single;

public interface SOService {

//    @Headers("app_token: dCuW7UQMbdvpcBDfzolAOSGFIcAec11a")
    @GET("api/0.4/?randomapi")
//    Call<SOProfileResponse> getProfile();
    Single<SOProfileResponse> getProfile();
}
