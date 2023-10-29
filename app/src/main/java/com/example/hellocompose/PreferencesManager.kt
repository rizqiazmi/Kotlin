package com.example.hellocompose

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager (context: Context){
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("auth", Context.MODE_PRIVATE)

    fun saveData(key: String, value: String){
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.putString("username", "rizqiazmi")
        editor.putString("role", "admin")
        editor.apply()
    }

    fun getData(key: String) : String{
//        var x = sharedPreferences.getString(key, "")
//        if(x == null){
//            x = ""
//        }
//        return x
        return sharedPreferences.getString(key, "") ?:""
    }
}