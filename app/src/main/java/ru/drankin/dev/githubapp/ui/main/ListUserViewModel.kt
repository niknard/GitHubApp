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
class ListUserViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {

    private val userList = MutableLiveData<List<User>>()

    init {
        GlobalScope.launch (Dispatchers.Main) {
            userRepository.getAllUsers().observeForever {
//                userList.value = mutableListOf()
                userList.value = it
            }

        }
    }

    fun delUser(id: Int) {
        GlobalScope.launch(Dispatchers.IO) {
            userRepository.delUserById(id)
        }
    }

    fun getUserList() : LiveData<List<User>> = userList

    fun addUser() = GlobalScope.launch (Dispatchers.IO) {
        val newUser = User(null, "Alex3")
        userRepository.addUser(newUser)
    }

}

