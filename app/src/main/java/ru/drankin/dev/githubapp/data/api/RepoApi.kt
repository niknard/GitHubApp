package ru.drankin.dev.githubapp.data.api

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import ru.drankin.dev.githubapp.data.model.Repo

interface RepoApi {
    @GET("/users/{userName}/repos")
    fun getReposAsync(@Path("userName") userName: String): Deferred<List<Repo>>
}