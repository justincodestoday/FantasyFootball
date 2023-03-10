package com.fantasy.fantasyfootball.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.fantasy.fantasyfootball.data.model.Team
import com.fantasy.fantasyfootball.data.model.User
import com.fantasy.fantasyfootball.data.model.UserWithTeam
import com.fantasy.fantasyfootball.repository.TeamRepository
import com.fantasy.fantasyfootball.repository.UserRepository
import kotlinx.coroutines.launch

class ProfileViewModel(private val userRepo: UserRepository, private val teamRepo: TeamRepository): ViewModel() {
    val userTeam: MutableLiveData<UserWithTeam> = MutableLiveData()

    fun editUser(userId: Int, user: User) {
        viewModelScope.launch {
            userRepo.editUser(userId, user)
        }
    }

    fun editTeam(teamId: Int, team: Team) {
        viewModelScope.launch {
            teamRepo.editTeam(teamId, team)
        }
    }

    fun getUserWithTeam(userId: Int) {
        viewModelScope.launch {
            val res = userRepo.getUserWithTeam(userId)
            res?.let {
                userTeam.value = it
            }
        }
    }

    class Provider(private val userRepo: UserRepository, private val teamRepo: TeamRepository): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ProfileViewModel(userRepo, teamRepo) as T
        }
    }
}