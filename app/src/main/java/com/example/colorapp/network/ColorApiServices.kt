package com.example.colorapp.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

data class ColorResponse(val hex: Hex, val name: Name)

data class Hex(val value: String)
data class Name(val value: String)

interface ColorApiServices {
    @GET("id")
    fun getColorByHex(@Query("hex") hexCode: String): Call<ColorResponse>
}