package com.example.hellocompose.service

import com.example.hellocompose.data.UpdateData
import com.example.hellocompose.respon.LoginRespon
import com.example.hellocompose.respon.UserRespon
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {
    @GET("users")
    fun getData(@Query("filters[username][\$contains]") search: String?) : Call<List<UserRespon>>

    @DELETE("users/{id}")
    fun delete(@Path("id") id : Int) : Call<UserRespon>

    @PUT("users/{id}")
    fun save(@Path("id") id: String?, @Body body: UpdateData) : Call<LoginRespon>
}