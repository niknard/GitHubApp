package ru.drankin.dev.githubapp.ui.base

import android.os.Bundle
import android.util.Log
import androidx.core.os.bundleOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.drankin.dev.githubapp.tools.SingleLiveEvent

open class BaseViewModel : ViewModel() {

    private val navigate = SingleLiveEvent<NavigateTo>()
    var bundle : Bundle? = null

    fun getNavigateLiveData() : LiveData<NavigateTo> = navigate

    fun setNavigateLiveData(navigateTo: NavigateTo) {
        GlobalScope.launch(Dispatchers.Main) {
            setNavigateLiveData(navigateTo, null)
        }
    }

    fun setNavigateLiveData(navigateTo: NavigateTo, bundle: Bundle?) {
        Log.d("abcd", "Navigate: $bundle")
        this.bundle = bundle
        navigate.value = navigateTo
    }

}

enum class NavigateTo {
    TO_ADDUSER,
    TO_REPO,
    TO_SETTINGS,
    TO_PREPAREDB,
    TO_BACK,
    TO_NOTHING
}

