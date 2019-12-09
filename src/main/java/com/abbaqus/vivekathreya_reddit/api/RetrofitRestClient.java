package com.abbaqus.vivekathreya_reddit.api;

import android.content.Context;

import com.abbaqus.vivekathreya_reddit.api.interceptor.NetworkConnectionInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static com.abbaqus.vivekathreya_reddit.utils.Constants.BASE_URL;

public class RetrofitRestClient {
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl(BASE_URL).
            addConverterFactory(ScalarsConverterFactory.create()).
            addConverterFactory(GsonConverterFactory.create());


    public static <S> S createService(Class<S> serviceClass, Context context) {

        OkHttpClient client = httpClient.addInterceptor(new NetworkConnectionInterceptor(context))
                .readTimeout(30, TimeUnit.SECONDS).connectTimeout(30, TimeUnit.SECONDS).build();
        Retrofit retrofit = builder.client(client).build();
        return retrofit.create(serviceClass);
    }

}
