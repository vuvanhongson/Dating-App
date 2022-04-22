package com.example.tinder.viewmodel.home;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tinder.data.remote.ApiUtils;
import com.example.tinder.data.remote.RetrofitClient;
import com.example.tinder.data.remote.SOService;
import com.example.tinder.model.Result;
import com.example.tinder.model.SOProfileResponse;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<Result> resultlist;

    public HomeViewModel()
    {
        resultlist = new MutableLiveData<>();
        makeApiCall();
    }

    public MutableLiveData<Result> getResult()
    {
        return  resultlist;
    }

    public void makeApiCall()
    {
//        SOService retrofitClient = RetrofitClient.getClient(BASE_URL).create(SOService.class);
//        retrofitClient.getProfile()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(getResultObserverRx());

        SOService mService = ApiUtils.getInstance().getSOService();
        mService.getProfile()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(soProfileResponse -> resultlist.postValue(soProfileResponse.getResults().get(0)));
//                .subscribe(getResultObserverRx());
    }

    private Observer<Result> getResultObserverRx()
    {
        return  new Observer<Result>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                resultlist.postValue(null);
            }

            @Override
            public void onNext(Result result) {
                resultlist.postValue(result);
                Log.d("profile1", "Profile of: " + result.toString());
            }
        };
    }


}
