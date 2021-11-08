package ru.drankin.dev.githubapp.ui.main

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
import javax.inject.Inject

@HiltViewModel
class AddUserViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {

    private val user = MutableLiveData<User>(User(null, ""))

    fun getUser(): LiveData<User> = user

    fun setUserName(name: String) {
        user.value?.userName=name
    }

    fun loadUser(id : Int) = GlobalScope.launch (Dispatchers.Main) {
        userRepository.findUserById(id).observeForever { user.value = it }
    }

    fun saveUser() = GlobalScope.launch (Dispatchers.IO) {
    if (user.value!=null)
        userRepository.addUser(user.value!!)
    }

}

