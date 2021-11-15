package ru.drankin.dev.githubapp.data.model

import androidx.room.Entity

@Entity
data class Issue (
    val url : String,
    val repository_url : String,
    val labels_url : String,
    val comments_url : String,
    val events_url : String,
    val html_url : String,
    val id : Int,
    val node_id : String,
    val number : Int,
    val title : String,
    val user : GitUser,
    val labels : List<String>,
    val state : String,
    val locked : Boolean,
    val assignee : String,
    val assignees : List<String>,
    val milestone : String,
    val comments : Int,
    val created_at : String,
    val updated_at : String,
    val closed_at : String,
    val author_association : String,
    val active_lock_reason : String,
    val body : String,
    val reactions : Reactions,
    val timeline_url : String,
    val performed_via_github_app : String
)

data class Reactions (
    val url : String,
    val total_count : Int,
    val laugh : Int,
    val hooray : Int,
    val confused : Int,
    val heart : Int,
    val rocket : Int,
    val eyes : Int
)

