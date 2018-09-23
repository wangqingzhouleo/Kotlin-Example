package com.example.leo.gitapp.Model

enum class Keys {
    USER,
    REPO,
    REPOS;

    val rawValue: String
        get() {
            return when (this) {
                USER -> "user"
                REPO -> "repo"
                REPOS -> "repos"
            }
        }
}