package com.example.webexchat.retrofiit.response

import com.google.gson.annotations.SerializedName

data class AccessTokenResponse (
	@SerializedName("access_token")
	val accessToken : String,
	@SerializedName("expires_in")
	val expiresIn : Long,
	@SerializedName("refresh_token")
	val refreshToken : String,
	@SerializedName("refresh_token_expires_in")
	val refreshTokenExpiresIn : Long
)