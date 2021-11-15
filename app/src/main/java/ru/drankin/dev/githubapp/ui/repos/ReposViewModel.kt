package ru.drankin.dev.githubapp.ui.repos

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.drankin.dev.githubapp.data.model.Repo
import ru.drankin.dev.githubapp.data.model.User
import ru.drankin.dev.githubapp.data.repository.RepoRepository
import ru.drankin.dev.githubapp.data.repository.UserRepository
import ru.drankin.dev.githubapp.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class ReposViewModel @Inject constructor(
    private val repoRepository: RepoRepository
): BaseViewModel() {

    private val _repoList = MutableLiveData<List<Repo>>()
    private val _onError = MutableLiveData<Boolean>(false)
    private val _isLoadig = MutableLiveData<Boolean>(false)
    private val _title = MutableLiveData<String>("")
    private var _userName : String? = null

    init {
        _repoList.observeForever(Observer {
            _isLoadig.value=false
        })
    }

    fun getTitle(): LiveData<String>{
        _title.value = "$_userName's repository"
        return  _title
    }

    fun setUserName(userName: String) {
        this._userName = userName
    }

    fun getOnErrorLiveData(): LiveData<Boolean> = _onError

    fun getLoadingState(): LiveData<Boolean> = _isLoadig

    fun getRepos(): LiveData<List<Repo>> {
        if (_userName!=null)
            viewModelScope.launch {
                try {
                    _isLoadig.value=true
                    val repos = repoRepository.getRepos(_userName!!)
                    _repoList.value = repos
                } catch (e: Exception) {
                    _isLoadig.value=false
                    _onError.value = true
                }
            }
        return _repoList
    }
}