package com.simbiose.model;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenRequest {

    @SerializedName("grant_type")
    private String grantType;

    @SerializedName("client_id")
    private String clientId;

    @SerializedName("client_secret")
    private String clientSecret;

    @SerializedName("code")
    private String code;

    @SerializedName("redirect_uri")
    private String redirectUri;

    @SerializedName("refresh_token")
    private String refreshToken;
}
