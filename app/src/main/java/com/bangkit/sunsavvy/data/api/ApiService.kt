package com.bangkit.sunsavvy.data.api

import com.bangkit.sunsavvy.data.apimodel.LoginResponse
import com.bangkit.sunsavvy.data.apimodel.PreferenceResponse
import com.bangkit.sunsavvy.data.apimodel.RegisterResponse
import com.bangkit.sunsavvy.data.apimodel.ResponseClassify
import com.bangkit.sunsavvy.data.apimodel.ResponseForecast
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT

interface ApiService {

    @FormUrlEncoded
    @POST("/api/auth/login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<LoginResponse>

    @FormUrlEncoded
    @POST("/api/auth/register")
    fun SignUp(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<RegisterResponse>

    @FormUrlEncoded
    @PUT("/api/auth/preference")
    fun register(
        @Field("email") email: String,
        @Field("name") name: String,
        @Field("skintype") skintype: String
    ): Call<PreferenceResponse>

    @FormUrlEncoded
    @POST("/api/uv/classify")
    fun takeSpf(
        @Header("Authorization") authorization: String,
        @Field("skin_type") skin_type: String,
        @Field("uv_index") uv_index : String
    ): Call<ResponseClassify>

    @FormUrlEncoded
    @POST("/api/uv/forecast")
    fun takeUv(
        @Header("Authorization") authorization: String,
        @Field("location") location : String,
        @Field("latitute") latitute : Double,
        @Field("longitude") longitude : Double
    ) : Call<ResponseForecast>
}