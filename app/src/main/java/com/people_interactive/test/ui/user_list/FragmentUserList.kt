package com.people_interactive.test.ui.user_list

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.gem.bridgeinspection.util.Utility
import com.people_interactive.test.AppController
import com.people_interactive.test.MainActivity
import com.people_interactive.test.R
import com.people_interactive.test.db.AppDatabase
import com.people_interactive.test.db.entities.TUser

class FragmentUserList : Fragment() {

    private lateinit var userViewModel: UserListViewModel
    private  var userAdapter : UserAdapter? = null
    private lateinit var recyclerUsers : RecyclerView
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?):
            View = inflater.inflate(R.layout.fragment_userlist, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerUsers = view.findViewById(R.id.recyclerUsers)
        progressBar = view.findViewById(R.id.progress)

        userViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(Application())
            .create<UserListViewModel>(
                UserListViewModel::class.java)

        userViewModel.userLiveData.observe(viewLifecycleOwner, Observer { it ->
            val result = it ?: return@Observer

            result.errorResponse?.let {
                Utility.showAlert(it, activity as MainActivity)
            }

            result.results?.let {
                Log.d("Data", "onViewCreated: $result")
                progressBar.visibility = View.GONE
                recyclerUsers.visibility = View.VISIBLE
                AppDatabase(AppController.appContext).shaadiDAO().insertAllUsers(it)
                fillUsers(it as ArrayList<TUser>)
            }

            result.exception?.let {
                Utility.showAlert(it?.message, activity as MainActivity)
            }
        })

        userViewModel.noInternet.observe(viewLifecycleOwner, Observer {
            Utility.showAlert(getString(R.string.no_internet), activity as MainActivity)
        })

        userViewModel.getUsers()
    }


    companion object {
         fun getInstance(): FragmentUserList {
             return FragmentUserList()
        }
    }

    private fun fillUsers(userList : ArrayList<TUser>)
    {
        userAdapter = UserAdapter(activity as MainActivity, userList)
        val linearLayoutManager = LinearLayoutManager(activity as MainActivity)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recyclerUsers.layoutManager = linearLayoutManager
        recyclerUsers.adapter = userAdapter
        val snalHelper = PagerSnapHelper()
        snalHelper.attachToRecyclerView(recyclerUsers)


    }

}