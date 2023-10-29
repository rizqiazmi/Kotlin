package com.example.hellocompose.service

import com.example.hellocompose.respon.UserRespon
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {
    @GET("users")
    fun getData() : Call<List<UserRespon>>

    @DELETE("users/{id}")
    fun delete(@Path("id") id : Int) : Call<UserRespon>
}