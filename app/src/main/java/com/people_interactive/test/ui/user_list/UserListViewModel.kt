package com.people_interactive.test.ui.user_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gem.bridgeinspection.util.Utility
import com.people_interactive.test.AppController
import com.people_interactive.test.db.AppDatabase
import com.people_interactive.test.repositories.UserRepository
import com.people_interactive.test.util.ConnectionDetector

class UserListViewModel: ViewModel() {

    private val _userLiveData = MutableLiveData<UserResult>()
    private val _noInternet = MutableLiveData<Boolean>()

    val userLiveData: LiveData<UserResult>
        get() = _userLiveData

    val noInternet: LiveData<Boolean>
        get() = _noInternet

    fun getUsers() {
        val users = AppDatabase(AppController.appContext).shaadiDAO().getUsers()
        if(users.isEmpty()) {
            if (ConnectionDetector().isConnectingToInternet(AppController.appContext)) {
                UserRepository.getInstance().getUsers() { isSuccess, errorResponse, response, exception ->
                    if (isSuccess) {
                        _userLiveData.postValue(UserResult(results = response!!.results))
                    } else if (errorResponse != null) {
                        _userLiveData.postValue(UserResult(errorResponse = errorResponse))
                    } else if (exception != null) {
                        _userLiveData.postValue(UserResult(exception = exception))
                    }
                }
            } else {
                _noInternet.value = true
            }
        }
        else
        {
            _userLiveData.postValue(UserResult(results = users))
        }
    }
}