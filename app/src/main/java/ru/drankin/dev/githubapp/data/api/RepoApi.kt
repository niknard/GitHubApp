package ru.drankin.dev.githubapp.data.api

import kotlinx.coroutines.Deferred
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*
import ru.drankin.dev.githubapp.data.model.GitUser
import ru.drankin.dev.githubapp.data.model.Repo
import ru.drankin.dev.githubapp.data.repository.ApiKeyRepository

interface RepoApi {

    @GET("/users/{userName}/repos")
    fun getReposAsync(@Path("userName") userName: String, @Header("Authorization") apiKey : String): Deferred<List<Repo>>

    @GET("/users/{userName}")
    fun getGitUser(@Path("userName") userName: String, @Header("Authorization") apiKey : String): Deferred<GitUser>

    @GET
    fun getImageFromURL(@Url url: String, @Header("Authorization") apiKey : String): Call<ResponseBody>
}