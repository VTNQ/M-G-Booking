package com.mgbooking.client.APIs;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

public class ApiClient {
    private static Retrofit retrofit=null;

    public static Retrofit getRetrofit(){
        Gson gson=new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
        HttpLoggingInterceptor logging=new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client=new OkHttpClient.Builder().addInterceptor(logging)  .connectTimeout(30, TimeUnit.SECONDS)  // Connection timeout
                .readTimeout(30, TimeUnit.SECONDS)     // Read timeout
                .writeTimeout(30, TimeUnit.SECONDS) .build();
        retrofit=new Retrofit.Builder().baseUrl("http://localhost:8686/api/").addConverterFactory(GsonConverterFactory.create(gson)).client(client).build();
        return retrofit;
    }
}
