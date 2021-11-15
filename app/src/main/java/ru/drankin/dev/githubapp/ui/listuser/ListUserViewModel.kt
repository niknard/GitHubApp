package ru.drankin.dev.githubapp.ui.main

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import ru.drankin.dev.githubapp.data.api.RepoApi
import ru.drankin.dev.githubapp.data.model.User
import ru.drankin.dev.githubapp.data.repository.RepoRepository
import ru.drankin.dev.githubapp.data.repository.UserRepository
import ru.drankin.dev.githubapp.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class ListUserViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val repoRepository: RepoRepository
): BaseViewModel() {

    private val userList = MutableLiveData<List<User>>()

    init {
        GlobalScope.launch (Dispatchers.Main) {
            userRepository.getAllUsers().observeForever {
//                userList.value = mutableListOf()
                userList.value = it
            }
        }

        updatePhotosInDB()

    }

    fun prepareDb(){
        GlobalScope.launch (Dispatchers.IO) {
            userRepository.prepareDB()
            updatePhotosInDB()
        }
    }

    fun delUser(id: Int) {
        GlobalScope.launch(Dispatchers.IO) {
            userRepository.delUserById(id)
        }
    }

    fun updatePhotosInDB() {
        viewModelScope.launch (Dispatchers.IO) {
            val allUsers = userRepository.getListOfAllUsers()
            for (user in allUsers) {
                updateUser(user)
            }
        }
    }

    fun updateUser(user : User) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("abcd", "update user ${user.userName}")

            try {
                val byteImage = repoRepository.getImageFromWeb(user.userName ?: "")
                user.photo = byteImage
                userRepository.addUser(user)
            } catch (e : Exception) {
                Log.d("abcd", "Unable to update users: ${e.message}")
            }
        }

    }

    fun getUserList() : LiveData<List<User>> = userList

}

