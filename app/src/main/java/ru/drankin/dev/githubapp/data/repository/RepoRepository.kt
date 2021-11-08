package ru.drankin.dev.githubapp.data.repository

import ru.drankin.dev.githubapp.data.api.RepoApi
import ru.drankin.dev.githubapp.data.model.Repo

class RepoRepository(private val repoApi: RepoApi) {
    suspend fun getRepos(userName : String) : List<Repo> {
        return repoApi.getReposAsync(userName).await()
    }
}