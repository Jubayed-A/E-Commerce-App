package com.jubayedalam.e_commerce_app.utils.sharedPreferences

import android.content.Context

class SharedPreferencesManager(context: Context) {
    private val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        val editor = sharedPreferences.edit()
        editor.putString("token", token)
        editor.apply()
    }
    fun saveUserId(id: Int) {
        val editor = sharedPreferences.edit()
        editor.putInt("userId", id)
        editor.apply()
    }

    fun getToken(): String? {
        return sharedPreferences.getString("token", null)
    }

    fun clearToken() {
        val editor = sharedPreferences.edit()
        editor.remove("token")
        editor.apply()
    }

    fun isLoggedIn(): Boolean {
        return sharedPreferences.contains("token")
    }

}



