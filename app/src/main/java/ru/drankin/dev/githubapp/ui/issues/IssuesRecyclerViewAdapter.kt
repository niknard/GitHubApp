package ru.drankin.dev.githubapp.ui.main

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import ru.drankin.dev.githubapp.R
import ru.drankin.dev.githubapp.data.model.Issue
import ru.drankin.dev.githubapp.data.model.Repo
import ru.drankin.dev.githubapp.data.model.User

class IssuesRecyclerViewAdapter(var issues: List<Issue>): RecyclerView.Adapter<IssuesRecyclerViewAdapter.MainHolder>(){

    class MainHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val login = itemView.findViewById<TextView>(R.id.login)
        private val state = itemView.findViewById<TextView>(R.id.state)
        private val id = itemView.findViewById<TextView>(R.id.id)
        private val title = itemView.findViewById<EditText>(R.id.title)
        private val body = itemView.findViewById<EditText>(R.id.body)


        fun bind(item: Issue){
            login.text = item.user.login
            state.text = item.state
            id.text = item.id.toString()
            title.setText(item.title)
            body.setText(item.body)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(LayoutInflater.from(parent.context).inflate(R.layout.issue_recycle_item, parent, false ))
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.bind(issues[position])
    }

    override fun getItemCount(): Int {
        return issues.size
    }
}
