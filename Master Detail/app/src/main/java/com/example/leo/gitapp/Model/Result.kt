package com.example.leo.gitapp.Model

class Result<T> {

    val responseType: ResponseType
    var value: T? = null
    var gitAppError: GitAppError? = null

    constructor(value: T) {
        this.value = value
        responseType = ResponseType.SUCCESS
    }

    constructor(gitAppError: GitAppError) {
        this.gitAppError = gitAppError
        responseType = ResponseType.ERROR
    }
}

enum class ResponseType {
    SUCCESS,
    ERROR;
}
