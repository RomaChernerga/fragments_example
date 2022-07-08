package com.example.mvp_mvvm_new.domain

import com.example.mvp_mvvm_new.domain.entities.UserProfile

    //CRUD
interface UserRepo {

    //create users
    fun addUser(user: UserProfile)

    //Read
    fun getAllUsers() : List<UserProfile>

    //update
    fun changeUser(id: String, user: UserProfile)

    //Delete
    fun deleteUser(id: String)

    //Delete All
    fun deleteAll()
}