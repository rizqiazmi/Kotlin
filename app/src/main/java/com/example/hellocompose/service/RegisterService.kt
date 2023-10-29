package com.example.hellocompose.service

import com.example.hellocompose.respon.LoginRespon
import com.example.hellocompose.data.RegisterData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterService {
    @POST("auth/local/register")
    fun saveData(@Body body: RegisterData) : Call<LoginRespon>
}