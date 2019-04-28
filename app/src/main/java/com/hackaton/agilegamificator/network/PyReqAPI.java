package com.hackaton.agilegamificator.network;

import com.hackaton.agilegamificator.presentation.shop.Bonus;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Dmitry Parshin on 27.04.2019.
 */
public interface PyReqAPI {

    @GET("account_id/{email}")
    Single<Response<Acc>> getAccountId(@Path(value = "email", encoded = true) String email);

    @GET("sprints/{accountId}")
    Single<Response<List<Sprint>>> getSprints(@Path("accountId") String accountId);

    @GET("balance/{accountId}")
    Single<Response<Balance>> getBalance(@Path("accountId") String accountId);

    @GET("dashboard")
    Single<Response<Dashboard>> getDashboard();

    @GET("bonuses")
    Single<Response<List<Bonus>>> getBonuses();

    @POST("change_balance/{accountId}")
    Single<Response<Void>> postBonusWasting(@Path("accountId") String accountId, int cost);
}
