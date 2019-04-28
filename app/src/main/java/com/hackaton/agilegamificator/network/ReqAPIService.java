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
public class ReqAPIService {

    private ReqAPI mReqApi;

    public ReqAPIService(@NonNull Retrofit retrofit) {
        this.mReqApi = retrofit.create(ReqAPI.class);
    }

    @NonNull
    public Single<Response<List<Person>>> getUsers(@NonNull String token, @NonNull String email) {
        return mReqApi.getUsers(token, email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<Response<IssueList>> getIssues(@NonNull String token, @NonNull String accountId) {
        return mReqApi.getIssues(token, accountId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
