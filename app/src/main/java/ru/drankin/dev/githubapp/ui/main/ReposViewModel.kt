package ru.drankin.dev.githubapp.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.drankin.dev.githubapp.data.model.Repo
import ru.drankin.dev.githubapp.data.model.User
import ru.drankin.dev.githubapp.data.repository.RepoRepository
import ru.drankin.dev.githubapp.data.repository.UserRepository
import javax.inject.Inject

@HiltViewModel
class ReposViewModel @Inject constructor(
    private val repoRepository: RepoRepository
): ViewModel() {

    private val _repoList = MutableLiveData<List<Repo>>()
    private val _onError = MutableLiveData<Boolean>(false)

    fun getOnErrorLiveData(): LiveData<Boolean> = _onError

    fun getRepos(userName: String): LiveData<List<Repo>> {
        viewModelScope.launch {
            try {
                val repos = repoRepository.getRepos(userName)
                _repoList.value = repos
            } catch (e: Exception) {
                _onError.value = true
            }
        }
        return _repoList
    }
}