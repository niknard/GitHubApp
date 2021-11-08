package ru.drankin.dev.githubapp.tools

class RVEvent {
    var eventType : EventType? = null
    var elementId : Int? = null
}

enum class EventType {
    Delete,
    Edit
}