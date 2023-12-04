package com.example.hellocompose.respon

import com.google.gson.annotations.SerializedName

//class Prodi{
//        var id: Int = 0
//        var nama_prodi:String = ""
//        var jumlah_peserta : String = ""
//}

data class Author(
        val id: Int,
        val name: String,
        val email: String
)

class UserRespon (
        var id: Int = 0,
        var username : String = "",
        var email : String = "",
        var provider : String = "",
        var confirmed: String = "",
        var blocked: Boolean = false,
        var createdAt : String = "",
        var updatedAt: String = "",
        @SerializedName("prodi")
        val prodi: Prodi
)

data class Prodi (
        val id: Long,
        @SerializedName("nama_prodi")
        val nameProdi: String,

        @SerializedName("jumlah_peserta")
        val namaPeserta: String,

        val createdAt: String?,
        val updatedAt : String?,
        val publishedAt : String

)
