package com.example.tinder.home;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

public class HomeViewModel extends ViewModel {

    private SOService mService = ApiUtils.getInstance().getSOService();;
    private Result result;
    private static List<Result> pro = new ArrayList<>();

    public HomeViewModel(){
        for(int a = 2; a <= 6; a++) {
                Log.d("MainActivity4", "Load i:" + a);
                loadAnswers();
        }
        Log.d("MainActivity", "new HomeViewModel");
    }

    public void loadAnswers() {
//        String.valueOf(per_page)
        Log.d("MainActivity", "start load Answers");
        result = new Result();
        mService.getProfile().enqueue(new Callback<SOProfileResponse>() {
            @Override
            public void onResponse(Call<SOProfileResponse> call, Response<SOProfileResponse> response) {

                if(response.isSuccessful()) {
                    result = response.body().getResults().get(0);
                    pro.add(result);
                    Log.d("MainActivity9", "This is: " + result.getUser().getName().getFirst());
                    Log.d("MainActivity0", "posts loaded from API");
                }else {
                    int statusCode = response.code();
                    // handle request errors depending on status code
                    Log.d("MainActivity0", "posts loaded not Successful");
                }
            }

            @Override
            public void onFailure(Call<SOProfileResponse> call, Throwable t) {
                Log.d("MainActivity0", "error loading from API");
                
            }
        });

        Log.d("MainActivity88", "Length Pro: " + pro.size());
        try{
            Log.d("MainActivity888", "This is profile of: " + pro.get(pro.size()-1).getUser().getName().getFirst());
        }
        catch (Exception e)
        {}
    }

    public List<Result> getProfileResponse() {
        Log.d("MainActivity", "get Profile API");
        Log.d("MainActivity8", "Length Pro: " + pro.size());
        return pro;
    }

    public int removeList(int i)
    {
        pro.remove(i);
        return 1;
    }
}
