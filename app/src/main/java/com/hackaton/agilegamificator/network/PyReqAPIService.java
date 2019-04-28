package com.hackaton.agilegamificator.network;

import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Dmitry Parshin on 27.01.2019.
 */
public class PyReqAPIService {

    private PyReqAPI mPyReqApi;

    public PyReqAPIService(@NonNull Retrofit retrofit) {
        this.mPyReqApi = retrofit.create(PyReqAPI.class);
    }

    public Single<Response<Acc>> getAccountId(String email) {
        return mPyReqApi.getAccountId(email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<Response<List<Sprint>>> getSprints(String accountId) {
        return mPyReqApi.getSprints(accountId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<Response<Dashboard>> getDashboard() {
        return mPyReqApi.getDashboard()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<Response<Balance>> getBalance(String accountId) {
        return mPyReqApi.getBalance(accountId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<Response<Void>> postBonusWasting(String accountId, int cost) {
        return mPyReqApi.postBonusWasting(accountId, cost)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
