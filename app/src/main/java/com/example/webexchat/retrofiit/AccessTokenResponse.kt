package com.example.webexchat.retrofiit

data class AccessTokenResponse (
	val access_token : String,
	val expires_in : Long,
	val refresh_token : String,
	val refresh_token_expires_in : Long
)