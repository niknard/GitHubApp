package ru.drankin.dev.githubapp.ui.issues

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.drankin.dev.githubapp.data.model.Issue
import ru.drankin.dev.githubapp.data.model.Repo
import ru.drankin.dev.githubapp.data.repository.RepoRepository
import ru.drankin.dev.githubapp.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class IssuesViewModel @Inject constructor(
    private val repoRepository: RepoRepository
): BaseViewModel() {

    private val _issuesList = MutableLiveData<List<Issue>>()
    private val _onError = MutableLiveData<Boolean>(false)
    private val _isLoadig = MutableLiveData<Boolean>(false)
    private val _title = MutableLiveData<String>("")
    private var _repoUrl : String? = null

    init {
        _issuesList.observeForever(Observer {
            _isLoadig.value=false
        })
    }

    fun getTitle(): LiveData<String> {
        _title.value = "Issues"
        return  _title
    }

    fun setRepo(url: String) {
        this._repoUrl = url
    }

    fun getOnErrorLiveData(): LiveData<Boolean> = _onError

    fun getLoadingState(): LiveData<Boolean> = _isLoadig

    fun getIssues(): LiveData<List<Issue>> {
        if (_repoUrl!=null)
            viewModelScope.launch {
                try {
                    _isLoadig.value=true
                    val issues = repoRepository.getIssues(_repoUrl!!)
                    _issuesList.value = issues
                } catch (e: Exception) {
                    _isLoadig.value=false
                    _onError.value = true
                }
            }
        return _issuesList
    }

    fun getTitleFromUrl(repoName : String): String {
        return repoName.substringAfterLast("/")
    }
}