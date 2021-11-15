package ru.drankin.dev.githubapp.ui.adduser

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.drankin.dev.githubapp.data.model.User
import ru.drankin.dev.githubapp.data.repository.UserRepository
import ru.drankin.dev.githubapp.ui.base.BaseViewModel
import ru.drankin.dev.githubapp.ui.base.NavigateTo
import javax.inject.Inject

@HiltViewModel
class AddUserViewModel @Inject constructor(
    private val userRepository: UserRepository
): BaseViewModel() {

    private val user = MutableLiveData<User>(User(null, ""))
    private val title = MutableLiveData<String>("Add user")

    fun getUser(): LiveData<User> = user

    fun setUserName(name: String) {
        user.value?.userName=name
    }

    fun cancel(){
        setNavigateLiveData(NavigateTo.TO_BACK)
    }

    fun loadUser(id : Int?) = GlobalScope.launch (Dispatchers.Main) {
        if (id!=null) {
            userRepository.findUserById(id).observeForever {
                title.value = "Edit ${it.userName}"
                user.value = it
            }
        }
    }

    fun getTitle(): LiveData<String> {
        return title
    }

    fun saveUser(userName: String) = GlobalScope.launch (Dispatchers.IO) {
    if (user.value!=null)
        user.value?.userName=userName
        userRepository.addUser(user.value!!)
        setNavigateLiveData(NavigateTo.TO_BACK)
    }

}

