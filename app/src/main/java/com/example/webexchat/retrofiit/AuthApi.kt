package com.example.webexchat.retrofiit

import retrofit2.Call
import retrofit2.http.*


interface AuthApi {
    @FormUrlEncoded
    @POST("/access_token")
    fun accessToken(
        @Field("grant_type") grant_type: String,
        @Field("client_id") client_id: String,
        @Field("client_secret") client_secret: String,
        @Field("code") code: String,
        @Field("redirect_uri") redirect_uri: String
    ): Call<AccessTokenResponse>?
}