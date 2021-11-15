package ru.drankin.dev.githubapp.ui.settings

import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.OkHttpClient
import ru.drankin.dev.githubapp.data.repository.ApiKeyRepository
import ru.drankin.dev.githubapp.data.repository.RepoRepository
import ru.drankin.dev.githubapp.ui.base.BaseViewModel
import ru.drankin.dev.githubapp.ui.base.NavigateTo
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val apiKeyRepository: ApiKeyRepository
): BaseViewModel() {

    @Inject
    lateinit var okHttpClient: OkHttpClient

    fun getApiKey(): String{
        val apiKey = apiKeyRepository.getApiKey()
        return apiKey?:""
    }

    fun setApiKey(apiKey: String){
        apiKeyRepository.setApiKey(apiKey)
        setNavigateLiveData(NavigateTo.TO_BACK)
    }

    fun cancel(){
        setNavigateLiveData(NavigateTo.TO_BACK)
    }
}