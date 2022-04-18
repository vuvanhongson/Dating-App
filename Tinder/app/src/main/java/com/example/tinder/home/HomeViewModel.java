package com.example.tinder.home;

import android.util.Log;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tinder.data.model.Result;
import com.example.tinder.data.model.SOProfileResponse;
import com.example.tinder.data.remote.ApiUtils;
import com.example.tinder.data.remote.SOService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Scheduler;
import rx.SingleSubscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HomeViewModel extends ViewModel {

    private SOService mService ;
    private Result result;
    private static List<Result> pro ;
    private MutableLiveData<Result> liveData;

    public MutableLiveData<Result> getLiveData() {

//        Log.d("getprofile", " "+ liveData.getValue().toString());
        return liveData;
    }

    public List<Result> getPro() {
        return pro;
    }

    public HomeViewModel() {
        this.liveData = new MutableLiveData<>();
        mService = ApiUtils.getInstance().getSOService();
        for(int i = 0; i < 5; i++) {
            loadAnswers();
        }
    }

    public void loadAnswers() {
        Log.d("MainActivity", "start load Answers");
            mService.getProfile()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleSubscriber<SOProfileResponse>() {
                        @Override
                        public void onSuccess(SOProfileResponse value) {
                            liveData.postValue(value.getResults().get(0));
                            pro.add(liveData.getValue());
                            Log.d("MainActivity123", "load Answers onSuccess");
                        }

                        @Override
                        public void onError(Throwable error) {
                            Log.d("MainActivity0", "load Answers onError");
                        }
                    });
    }

}
