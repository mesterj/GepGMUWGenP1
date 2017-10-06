package com.kite.joco.gepgmuwgenp1;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by Mester József on 2017. 10. 04..
 */

public class GepService {

    //http://localhost:8080/GepAtvetServP1-1.0-SNAPSHOT/webresources/entities.tabletsorszam/1
    private static final String API_BASE_URL = "http://192.168.86.2/GepAtvetServP1-1.0-SNAPSHOT/webresources/";//éles

    private static final OkHttpClient httpClient = new OkHttpClient();

    private static final Retrofit.Builder builder = new Retrofit.Builder().baseUrl(API_BASE_URL);


        /*public static <S> S createService(Class<S> serviceClass) {
            Retrofit retrofit = builder.client(httpClient).build();
            return retrofit.create(serviceClass);
        }*/

    public static GepAPI getGepRestService() {
        Retrofit retrofit = builder.client(httpClient).build();
        return retrofit.create(GepAPI.class);
    }
}
