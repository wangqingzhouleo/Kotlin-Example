package com.example.leo.gitapp

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.example.leo.gitapp.Model.*
import org.json.JSONArray
import org.json.JSONObject

class RequestManager {

    private val baseUrl = "https://api.github.com"

    companion object {
        val shared = RequestManager()
    }

    fun loadUser(username: String, completion: (Result<User>) -> Unit) {
        val userUrl = "$baseUrl/users/$username"
        AndroidNetworking.get(userUrl)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject?) {
                        val result = response.let { it } ?: return completion(Result(GitAppError.PARSE_RESULT_FAILED))
                        completion(Result(User(result)))
                    }

                    override fun onError(anError: ANError?) {
                        completion(Result(parseAnError(anError)))
                    }

                })
    }

    fun loadReposWithUsername(username: String, completion: (Result<List<Repo>>) -> Unit) {
        val url = "$baseUrl/users/$username/repos"
        loadReposWithUrl(url, 100, completion)
    }

    fun loadReposFromUser(user: User, completion: (Result<List<Repo>>) -> Unit) {
        loadReposWithUrl(user.reposUrl, 100, completion)
    }

    fun loadReposWithUrl(url: String, perPage: Int? = null, completion: (Result<List<Repo>>) -> Unit) {
        val perPage = perPage ?: 100
        val repoUrl = "$url?per_page=$perPage"
        AndroidNetworking.get(repoUrl)
                .build()
                .getAsJSONArray(object : JSONArrayRequestListener {
                    override fun onResponse(response: JSONArray?) {
                        val result = response.let { it } ?: return completion(Result(GitAppError.PARSE_RESULT_FAILED))
                        if (result.length() == 0) {
                            return completion(Result(GitAppError.NO_RESULT))
                        }
                        val list = MutableList(result.length()) { index ->
                            Repo(result.getJSONObject(index))
                        }
                        completion(Result(list))
                    }

                    override fun onError(anError: ANError?) {
                        completion(Result(parseAnError(anError)))
                    }

                })
    }

    private fun parseAnError(anError: ANError?): GitAppError {
        return when (anError?.errorCode) {
            404 -> GitAppError.USER_NOT_FOUND
            else -> GitAppError.REQUEST_FAILED
        }
    }
}