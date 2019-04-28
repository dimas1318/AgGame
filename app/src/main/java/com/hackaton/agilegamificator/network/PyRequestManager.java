package com.hackaton.agilegamificator.network;

import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Dmitry Parshin on 27.01.2019.
 */
public class PyRequestManager {

    private static PyRequestManager sInstance;

    private PyReqAPIService mPyReqService;

    private PyRequestManager() {
        Retrofit retrofit = PyRetrofitFactory.getRetrofit();

        mPyReqService = new PyReqAPIService(retrofit);
    }

    @NonNull
    public static PyRequestManager getInstance() {
        if (sInstance == null) {
            synchronized (PyRequestManager.class) {
                if (sInstance == null) {
                    sInstance = new PyRequestManager();
                }
            }
        }
        return sInstance;
    }

    public Single<Response<Acc>> getAccountId(String email) {
        return mPyReqService.getAccountId(email);
    }

    public Single<Response<List<Sprint>>> getSprints(String accountId) {
        return mPyReqService.getSprints(accountId);
    }

    public Single<Response<Dashboard>> getDashboard() {
        return mPyReqService.getDashboard();
    }

    public Single<Response<Balance>> getBalance(String accountId) {
        return mPyReqService.getBalance(accountId);
    }
}
