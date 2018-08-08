package com.simbiose.service;

import com.simbiose.model.TokenRequest;
import com.simbiose.model.TokenResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;


public interface MercadoLivre {

    @POST("oauth/token")
    Call<TokenResponse> requestToken(/*@Header("Authorization") String header, */@Body TokenRequest request);

}
