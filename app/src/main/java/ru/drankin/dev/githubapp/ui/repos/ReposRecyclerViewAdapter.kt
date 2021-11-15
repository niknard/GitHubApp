package ru.drankin.dev.githubapp.ui.main

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import ru.drankin.dev.githubapp.R
import ru.drankin.dev.githubapp.data.model.Repo
import ru.drankin.dev.githubapp.data.model.User

class ReposRecyclerViewAdapter(var repos: List<Repo>): RecyclerView.Adapter<ReposRecyclerViewAdapter.MainHolder>(){

    interface OnItemClickListener {
        fun onItemClick(action: ReposAdapterClickAction, repo: Repo)
    }

    var itemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener){
        itemClickListener = onItemClickListener
    }

    class MainHolder(itemView: View, private val onItemClickListener: OnItemClickListener?): RecyclerView.ViewHolder(itemView) {
        private val name = itemView.findViewById<TextView>(R.id.name)
        private val open_issuses = itemView.findViewById<TextView>(R.id.open_issuses)
        private val created_at = itemView.findViewById<TextView>(R.id.created_at)
        private val description = itemView.findViewById<EditText>(R.id.description)


        fun bind(item: Repo){
            if (item.open_issues>0){
                itemView.setOnClickListener {
                    Log.d("abcd", "Common")
                    onItemClickListener?.onItemClick(ReposAdapterClickAction.GoToIssue, item)
                }
            }

            name.text = item.name
            open_issuses.text = item.open_issues.toString()
            created_at.text = item.created_at
            description.setText(item.description)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(LayoutInflater.from(parent.context).inflate(R.layout.repo_recycle_item, parent, false ), itemClickListener)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.bind(repos[position])
    }

    override fun getItemCount(): Int {
        return repos.size
    }
}

enum class ReposAdapterClickAction {
    GoToIssue
}