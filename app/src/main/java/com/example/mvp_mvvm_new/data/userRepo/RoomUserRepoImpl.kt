package com.example.mvp_mvvm_new.data.userRepo

import com.example.mvp_mvvm_new.domain.UserRepo
import com.example.mvp_mvvm_new.domain.entities.UserProfile

class RoomUserRepoImpl: UserRepo {
    override fun addUser(user: UserProfile) {
        TODO("Not yet implemented")
    }

    override fun getAllUsers(): List<UserProfile> {
        TODO("Not yet implemented")

    }

    override fun changeUser(id: String, user: UserProfile) {
        TODO("Not yet implemented")
    }

    override fun deleteUser(id: String) {
        TODO("Not yet implemented")
    }

    override fun deleteAll() {
        TODO("Not yet implemented")
    }
}