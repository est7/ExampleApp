package com.example.mvvmjetpack.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.mvvmjetpack.data.db.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM user WHERE user_account = :account AND user_pwd = :pwd")
    fun login(account:String,pwd:String):Flow<User?>

    @Query("SELECT * FROM user WHERE id=:id")
    fun findUserById(id:Long): Flow<User>

    @Query("SELECT * FROM user")
    fun getAllUsers():List<User>


    @Insert
    fun insertUser(user:User):Long

    @Delete
    fun deleteUser(user: User)

    @Update
    fun updateUser(user:User)
}