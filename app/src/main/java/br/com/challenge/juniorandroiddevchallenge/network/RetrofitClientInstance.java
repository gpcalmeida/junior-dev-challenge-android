package br.com.radixeng.juniorandroiddevchallenge.network;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    private static Retrofit retrofit;

    /* A Api do StarWars que foi incialmente integrada ao projeto parece ter sido descontinuada
    *  por esse motivo integrei o app com uma cópia da mesma Api rodando no Back4App
    *  https://www.back4app.com/database/davimacedo/swapi-star-wars-api
     */
    private static final String BASE_URL = "http://parseapi.back4app.com";

    public static Retrofit getRetrofitInstance() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(chain -> {
            Request original = chain.request();

            Request request = original.newBuilder()
                    .addHeader("X-Parse-Application-Id", "qD0jK2WI9yKkBQ03n8TfPYTQ4rwjXQz9mfza5xjd")
                    .addHeader("X-Parse-REST-API-Key", "2y9btsj3LqE3VM7AFHrdEQeMbJU9eZMNI5VvCy1E")
                    .method(original.method(), original.body())
                    .build();

            return chain.proceed(request);
        });

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        }
        return retrofit;
    }

}
