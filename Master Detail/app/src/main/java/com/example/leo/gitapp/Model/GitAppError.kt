package com.example.leo.gitapp.Model

enum class GitAppError {
    NO_RESULT,
    INPUT_ERROR,
    PARSE_RESULT_FAILED,
    REQUEST_FAILED,
    USER_NOT_FOUND;

    fun getMessage(): String {
        return when (this) {
            NO_RESULT -> "No Result"
            INPUT_ERROR -> "Please enter correct username"
            PARSE_RESULT_FAILED -> " Parse Result Failed"
            REQUEST_FAILED -> "Request Failed"
            USER_NOT_FOUND -> "User Not Found"
        }
    }
}