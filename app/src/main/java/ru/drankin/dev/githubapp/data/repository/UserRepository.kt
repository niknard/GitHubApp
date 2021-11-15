package ru.drankin.dev.githubapp.data.repository

import androidx.lifecycle.LiveData
import ru.drankin.dev.githubapp.data.datasource.UserDAO
import ru.drankin.dev.githubapp.data.model.User

class UserRepository(private val userDAO: UserDAO) {
    suspend fun addUser(user: User) {
        userDAO.addUser(user)
    }

    suspend fun findUserById(id: Int): LiveData<User> {
        return userDAO.findUserById(id)
    }

    suspend fun getAllUsers(): LiveData<List<User>> {
        return userDAO.getAllUsers()
    }

    suspend fun getListOfAllUsers(): List<User> {
        return userDAO.getListOfAllUsers()
    }

    suspend fun delUser(user: User) {
        userDAO.delUser(user)
    }

    suspend fun delUserById(id: Int) {
        userDAO.delUserById(id)
    }

    suspend fun prepareDB() {
        userDAO.delAllUsers()
        userDAO.addUser(User(null, "niknard", null))
        userDAO.addUser(User(null, "mojombo", null))
        userDAO.addUser(User(null, "maria", null))
        userDAO.addUser(User(null, "alex", null))
        userDAO.addUser(User(null, "gorner", null))
        userDAO.addUser(User(null, "tomohawk", null))
        userDAO.addUser(User(null, "toreo", null))
        userDAO.addUser(User(null, "torik", null))
    }
}