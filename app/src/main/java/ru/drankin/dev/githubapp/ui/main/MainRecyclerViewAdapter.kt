package ru.drankin.dev.githubapp.ui.main

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import ru.drankin.dev.githubapp.R
import ru.drankin.dev.githubapp.data.model.User
import ru.drankin.dev.githubapp.tools.EventType
import ru.drankin.dev.githubapp.tools.RVEvent

class MainRecyclerViewAdapter(var users: List<User>): RecyclerView.Adapter<MainRecyclerViewAdapter.MainHolder>(){

    interface OnItemClickListener {
        fun onItemClick(action: MainAdapterClickAction, user: User)
    }

    var itemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener){
         itemClickListener = onItemClickListener
    }

    class MainHolder(itemView: View, private val onItemClickListener: OnItemClickListener?): RecyclerView.ViewHolder(itemView) {
        private val userName = itemView.findViewById<TextView>(R.id.userNameText)
        private val userPhoto = itemView.findViewById<ImageView>(R.id.userPhoto)

        fun bind(item: User){
            itemView.setOnClickListener {
                Log.d("abcd", "Common")
                onItemClickListener?.onItemClick(MainAdapterClickAction.GoToRepo, item)
            }
            itemView.findViewById<Button>(R.id.editBtn).setOnClickListener {
                Log.d("abcd", "Edit")
                onItemClickListener?.onItemClick(MainAdapterClickAction.EditButtonClick, item)
            }
            itemView.findViewById<ImageButton>(R.id.deleteBtn).setOnClickListener {
                Log.d("abcd", "Delete")
                onItemClickListener?.onItemClick(MainAdapterClickAction.DeleteButtonClick, item)
            }
            userName.text = item.userName

            val image = itemView.findViewById<ImageView>(R.id.userPhoto)
            if (item.photo==null) {
                image.setImageResource(R.drawable.ic_baseline_person_outline_24)
            } else {
                val bitmapUserPhoto = BitmapFactory.decodeByteArray(item.photo,0, item.photo!!.size)
                image.setImageBitmap(bitmapUserPhoto)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(LayoutInflater.from(parent.context).inflate(R.layout.main_recycle_item, parent, false ), itemClickListener)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int {
        return users.size
    }
}

enum class MainAdapterClickAction {
    EditButtonClick,
    DeleteButtonClick,
    GoToRepo
}