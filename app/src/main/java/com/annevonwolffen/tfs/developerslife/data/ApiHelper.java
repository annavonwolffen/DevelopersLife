package com.annevonwolffen.tfs.developerslife.data;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * @author Terekhova Anna
 */
public class ApiHelper {
    private static ApiHelper mInstance;
    private static final String BASE_URL = "https://developerslife.ru";
    private Retrofit mRetrofit;

    private ApiHelper() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    public static ApiHelper getInstance() {
        if (mInstance == null) {
            mInstance = new ApiHelper();
        }
        return mInstance;
    }

    public DevelopersLifeApi getApi() {
        return mRetrofit.create(DevelopersLifeApi.class);
    }

}
