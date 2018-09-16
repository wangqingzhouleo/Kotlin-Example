package com.example.leo.gitapp.Model

enum class Error {
    NO_RESULT,
    PARSE_RESULT_FAILED,
    REQUEST_FAILED,
    USER_NOT_FOUND;

    fun getMessage(): String {
        return when (this) {
            NO_RESULT -> "No Result"
            PARSE_RESULT_FAILED -> " Parse Result Failed"
            REQUEST_FAILED -> "Request Failed"
            USER_NOT_FOUND -> "User Not Found"
        }
    }
}