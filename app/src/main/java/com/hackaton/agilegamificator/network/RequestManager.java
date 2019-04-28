package com.hackaton.agilegamificator.network;

import android.support.annotation.NonNull;
import android.util.Base64;

import com.hackaton.agilegamificator.Constants;

import java.nio.charset.StandardCharsets;
import java.util.List;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Dmitry Parshin on 27.01.2019.
 */
public class RequestManager {

    private static RequestManager sInstance;

    private ReqAPIService mReqService;

    private RequestManager() {
        Retrofit retrofit = RetrofitFactory.getRetrofit();

        mReqService = new ReqAPIService(retrofit);
    }

    @NonNull
    public static RequestManager getInstance() {
        if (sInstance == null) {
            synchronized (RequestManager.class) {
                if (sInstance == null) {
                    sInstance = new RequestManager();
                }
            }
        }
        return sInstance;
    }

    @NonNull
    public Single<Response<List<Person>>> getUsers(@NonNull String email) {
        byte[] data = (Constants.ADMIN_EMAIL + ":" + Constants.TOKEN).getBytes(StandardCharsets.UTF_8);
        String authData = "Basic " + Base64.encodeToString(data, Base64.NO_WRAP);
        return mReqService.getUsers(authData, email);
    }

    @NonNull
    public Single<Response<IssueList>> getIssues(@NonNull String accountId) {
        byte[] data = (Constants.ADMIN_EMAIL + ":" + Constants.TOKEN).getBytes(StandardCharsets.UTF_8);
        String authData = "Basic " + Base64.encodeToString(data, Base64.NO_WRAP);
        return mReqService.getIssues(authData, "assignee=" + accountId);
    }
}
