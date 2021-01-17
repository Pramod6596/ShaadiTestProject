package com.people_interactive.test.ui.user_list

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.people_interactive.test.AppController
import com.people_interactive.test.R
import com.people_interactive.test.db.AppDatabase
import com.people_interactive.test.db.entities.TUser

class UserAdapter(private val activity: Activity, private var userModels: List<TUser>) : RecyclerView.Adapter<UserAdapter.UserHolder>()
{
    var userHolder : UserHolder? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): UserHolder {
        val v: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_user, viewGroup, false)
        return UserHolder(v)
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        try {
            val appDAO = AppDatabase(AppController.appContext).shaadiDAO()
            userHolder = holder
            val tUser = userModels[position]
            userHolder!!.tvName.text = "Name : "+tUser.name.title+" "+tUser.name.first+" "+tUser.name.last
            userHolder!!.tvMobile.text = "Phone : " +tUser.phone
            userHolder!!.tvEmail.text = "Email : "+tUser.email
            userHolder!!.tvCity.text = "City : "+tUser.location.city
            userHolder!!.tvState.text = "State : "+tUser.location.state
            userHolder!!.tvGender.text = "Gender : "+tUser.gender
            userHolder!!.tvAge.text = "Age : "+tUser.dob.age
            userHolder!!.tvCountry.text = "Country : "+tUser.location.country

            Glide.with(activity)
                    .load(tUser.picture.large)
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)
                    .skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .circleCrop()
                    .into(userHolder!!.imgUser)

            userHolder!!.btnAccept.setOnClickListener {
                Log.d("Accept", "onBindViewHolder: Deny")

                tUser.status = 1
                appDAO.updateUser(tUser)
                notifyDataSetChanged()
            }

            userHolder!!.btnDeny.setOnClickListener {
                Log.d("Deny", "onBindViewHolder: Deny")
                tUser.status = 2
                appDAO.updateUser(tUser)
                notifyDataSetChanged()
            }

            when(tUser.status)
            {
                1-> {
                    userHolder!!.btnAccept.visibility = View.GONE
                    userHolder!!.btnDeny.visibility = View.GONE
                    userHolder!!.tvmessage.visibility = View.VISIBLE
                    userHolder!!.tvmessage.text = "Memeber Accepted"
                    userHolder!!.tvmessage.setTextColor(activity.resources.getColor(R.color.green))
                }
                2-> {
                    userHolder!!.btnAccept.visibility = View.GONE
                    userHolder!!.btnDeny.visibility = View.GONE
                    userHolder!!.tvmessage.visibility = View.VISIBLE
                    userHolder!!.tvmessage.text = "Memeber Denied"
                    userHolder!!.tvmessage.setTextColor(activity.resources.getColor(R.color.red))
                }
            }

        } catch (t: Throwable) {
            t.printStackTrace()
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        super.getItemViewType(position)
        return position
    }

    override fun getItemCount(): Int {
        return if (userModels != null) {
            userModels!!.size
        } else 0
    }

    inner class UserHolder(itemView: View) :  RecyclerView.ViewHolder(itemView) {
        var imgUser: ImageView
        var tvName: TextView
        var tvMobile: TextView
        var tvEmail: TextView
        var tvGender: TextView
        var tvCity: TextView
        var tvState: TextView
        var tvmessage: TextView
        var tvAge: TextView
        var tvCountry: TextView
        var btnAccept : Button
        var btnDeny : Button

        init {
            imgUser = itemView.findViewById(R.id.imgUser)
            tvName = itemView.findViewById(R.id.tvName)
            tvMobile = itemView.findViewById(R.id.tvMobile)
            tvEmail = itemView.findViewById(R.id.tvEmail)
            tvGender = itemView.findViewById(R.id.tvGender)
            tvCity = itemView.findViewById(R.id.tvCity)
            tvState = itemView.findViewById(R.id.tvState)
            tvAge = itemView.findViewById(R.id.tvAge)
            tvCountry = itemView.findViewById(R.id.tvCountry)
            tvmessage = itemView.findViewById(R.id.tvMessage)
            btnAccept = itemView.findViewById(R.id.btnAccept)
            btnDeny = itemView.findViewById(R.id.btnDeny)
        }
    }
}