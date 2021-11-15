package ru.drankin.dev.githubapp.data.datasource

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.Deferred
import ru.drankin.dev.githubapp.data.model.User

@Dao
interface UserDAO {
    @Insert(entity = User::class, onConflict = OnConflictStrategy.REPLACE)
    fun addUser(user: User)

    @Query("SELECT * from User")
    fun getAllUsers(): LiveData<List<User>>

    @Query("SELECT * from User")
    fun getListOfAllUsers(): List<User>

    @Query("SELECT * from User where id = :id")
    fun findUserById(id : Int): LiveData<User>

    @Delete(entity = User::class)
    fun delUser(user: User)

    @Query("DELETE from User where id = :id")
    fun delUserById(id: Int)

    @Query("DELETE from User")
    fun delAllUsers()
}