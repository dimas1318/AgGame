package com.hackaton.agilegamificator.network;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hackaton.agilegamificator.Constants;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Dmitry Parshin on 27.04.2019.
 */
public class PyRetrofitFactory {

    @NonNull
    public static Retrofit getRetrofit() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS)
                .build();

        return new Retrofit.Builder()
                .baseUrl(Constants.PY_BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(/*gson*/))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}
