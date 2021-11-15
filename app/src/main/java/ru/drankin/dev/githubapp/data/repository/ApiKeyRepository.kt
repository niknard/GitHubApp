package ru.drankin.dev.githubapp.data.repository

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

class ApiKeyRepository(private val context: Context) {
    private val prefSettings = context.getSharedPreferences("api", Context.MODE_PRIVATE)
    private var apiKey : String = prefSettings.getString("ApiKey", "")?:""

    fun getApiKey(): String {
        Log.d("abcd", "GetAPIkey:$apiKey")
        return apiKey
    }

    fun setApiKey(apiKey: String) {
        prefSettings.edit()
            .putString("ApiKey", apiKey)
            .apply()
        this.apiKey = apiKey
    }
}