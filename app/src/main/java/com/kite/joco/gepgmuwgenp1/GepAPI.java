package com.kite.joco.gepgmuwgenp1;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PUT;

/**
 * Created by Mester József on 2017. 10. 04..
 */

public interface GepAPI {


    //@Headers( "Content-Type: application/json")
    @PUT("entities.tabletsorszam/1")
    Call<Gmuwsorszam> edit(@Body Gmuwsorszam gmuw);

    @GET("entities.tabletsorszam/1")
    Call<Gmuwsorszam> getSorszam();
}
