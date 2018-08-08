package com.simbiose.model;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "token")
public class TokenResponse {

    @Id
    private String id;

    @CreatedDate
    private LocalDateTime createdOn;

    @SerializedName("token_type")
    public String tokenType;

    @SerializedName("access_token")
    public String accessToken;

    @SerializedName("refresh_token")
    private String refreshToken;

    @SerializedName("expires_in")
    public Integer expiresIn;

    @SerializedName("scope")
    public String scope;
}
