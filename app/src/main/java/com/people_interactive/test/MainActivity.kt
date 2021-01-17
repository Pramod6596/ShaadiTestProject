package com.people_interactive.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.people_interactive.test.navigation.NavigationItems
import com.people_interactive.test.ui.user_list.FragmentUserList

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setFragment(NavigationItems.UserList,false, resources.getString(R.string.user_list))
    }

    fun setFragment(navigationItems: NavigationItems, addToBackStack : Boolean, tag : String) {

        val fragmentTransaction = supportFragmentManager.beginTransaction()

        var fragment: Fragment = FragmentUserList.getInstance()

        when (navigationItems) {

            is NavigationItems.UserList -> {
                fragment = FragmentUserList.getInstance()
            }
        }

        if(addToBackStack)
        {
            fragmentTransaction.addToBackStack(tag)
        }

        fragmentTransaction.replace(R.id.mainLayout, fragment)
        fragmentTransaction.commitAllowingStateLoss()
    }

}