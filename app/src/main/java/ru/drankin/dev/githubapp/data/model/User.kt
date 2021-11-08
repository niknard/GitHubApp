package ru.drankin.dev.githubapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class User(
        @PrimaryKey(autoGenerate = true)
        var id: Int?,
        var userName: String?
    )
