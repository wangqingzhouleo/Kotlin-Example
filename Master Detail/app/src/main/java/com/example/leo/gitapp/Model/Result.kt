package com.example.leo.gitapp.Model

import com.androidnetworking.error.ANError

class Result<T> {

    val responseType: ResponseType
    var value: T? = null
    var error: Error? = null

    constructor(value: T) {
        this.value = value
        responseType = ResponseType.SUCCESS
    }

    constructor(error: Error) {
        this.error = error
        responseType = ResponseType.ERROR
    }
}

enum class ResponseType {
    SUCCESS,
    ERROR;
}