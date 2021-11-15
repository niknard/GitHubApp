package ru.drankin.dev.githubapp.data.repository

import android.graphics.BitmapFactory
import android.util.Log
import android.widget.ImageView
import com.squareup.picasso.Picasso
import retrofit2.Retrofit
import ru.drankin.dev.githubapp.data.api.RepoApi
import ru.drankin.dev.githubapp.data.model.Issue
import ru.drankin.dev.githubapp.data.model.Repo

class RepoRepository(private val repoApi: RepoApi, private val apiKeyRepository: ApiKeyRepository) {

    fun apiKey():String {
        val apiKey = apiKeyRepository.getApiKey()
        if (apiKey=="")
            return ""
        else
            return "token $apiKey"
    }

    suspend fun getRepos(userName : String) : List<Repo> {
        Log.d("abcd", "getRepos apiKey=${apiKey()}")
        return repoApi.getReposAsync(userName, apiKey()).await()
    }

    suspend fun getIssues(url: String) : List<Issue> {
        return repoApi.getIssuesFromURL(url, apiKey()).await()
    }

    suspend fun getImageFromWeb(userName: String):ByteArray? {
        Log.d("abcd", "getRepos apiKey=${apiKey()}")
        val user = repoApi.getGitUser(userName, apiKey()).await()
        val avatar_url = user.avatar_url
        val body = repoApi.getImageFromURL(avatar_url, apiKey()).execute().body()
        val bytes = body?.bytes()
        return bytes
    }
}