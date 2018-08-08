package com.simbiose.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.simbiose.model.TokenRequest;
import com.simbiose.model.TokenResponse;
import com.simbiose.repository.MercadoLivreRepository;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.Optional;

@Component
public class MercadoLivreService {

    @Value("${ml.clientID}")
    private String clientID;
    @Value("${ml.clientSecret}")
    private String clientSecret;
    @Value("${ml.redirect.uri}")
    private String redirectUri;

    private MercadoLivre mercadoLivre;

    @Autowired
    private MercadoLivreRepository mercadoLivreRepository;

    /**
     * GERA UM OBJETO RETROFIT PARA A INTERFACE MERCADO LIVRE
     */
    public MercadoLivreService() {
        OkHttpClient client = new OkHttpClient
                .Builder()
                .addInterceptor(new CallInterceptor())
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.mercadolibre.com/")
                .addConverterFactory(factory())
                .client(client)
                .build();

        mercadoLivre = retrofit.create(MercadoLivre.class);
    }

    /**
     * CONVERTER OS RETORNOS DO CONSUMO DA API NO FORMATO JSON
     *
     * @return
     */
    public GsonConverterFactory factory() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .enableComplexMapKeySerialization().create();
        return GsonConverterFactory.create(gson);
    }

    public void requestToken(String code) throws IOException {

        TokenRequest tokenRequest = TokenRequest.builder()
                .clientId(clientID)
                .clientSecret(clientSecret)
                .grantType("authorization_code")
                .code(code)
                .redirectUri(redirectUri)
                .build();

        Response<TokenResponse> response = mercadoLivre.requestToken(tokenRequest).execute();

        if (response.raw().code() == 200) {
//            return Optional.ofNullable(response.body());
            mercadoLivreRepository.save(response.body());
        }

        throw new IllegalArgumentException("Illegal code");
    }

    public void refreshToken() throws IOException {
        TokenRequest tokenRequest = TokenRequest.builder()
                .clientId(clientID)
                .clientSecret(clientSecret)
                .grantType("refresh_token")
                .refreshToken(mercadoLivreRepository.findFirstByOrderByCreatedOnDesc().getRefreshToken())
                .redirectUri(redirectUri)
                .build();

        Response<TokenResponse> response = mercadoLivre.requestToken(tokenRequest).execute();

        if (response.raw().code() == 200) {
            mercadoLivreRepository.save(response.body());
        }
    }
}
