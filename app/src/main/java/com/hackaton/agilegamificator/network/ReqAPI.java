package com.hackaton.agilegamificator.network;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by Dmitry Parshin on 27.04.2019.
 */
public interface ReqAPI {

    @GET("user/search")
    Single<Response<List<Person>>> getUsers(@Header("Authorization") String token,
                                            @Query(value = "username", encoded = true) String email);

    @GET("search")
    Single<Response<IssueList>> getIssues(@Header("Authorization") String token,
                                            @Query("jql") String accountId);
}
