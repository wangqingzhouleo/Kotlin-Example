package com.example.leo.githook.Managers

import android.content.Context
import android.widget.Toast
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.example.leo.githook.Models.Error
import com.example.leo.githook.Models.Repo
import com.example.leo.githook.Models.User
import org.json.JSONArray
import org.json.JSONObject

val requestManager = RequestManager()

class RequestManager {

    private val baseUrl = "https://api.github.com"

    fun loadUser(context: Context, username: String, completion: (User) -> Unit) {
        val userUrl = "$baseUrl/users/$username"
        AndroidNetworking.get(userUrl)
                .build()
                .getAsJSONObject(object: JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject?) {
                        val result = response.let { it } ?: return showError(context, Error.PARSE_RESULT_FAILED)
                        completion(User(result))
                    }

                    override fun onError(anError: ANError?) {
                        if (anError?.errorCode == 404) {
                            showError(context, Error.USER_NOT_FOUND)
                        } else {
                            showError(context, Error.REQUEST_FAILED)
                        }
                    }
                })
    }

    fun loadReposWithName(context: Context, login: String, completion: (List<Repo>) -> Unit) {
        val repoUrl = "$baseUrl/users/$login/repos"
        loadReposWithUrl(context, repoUrl, completion)
    }

    fun loadReposWithUrl(context: Context, url: String, completion: (List<Repo>) -> Unit) {
        AndroidNetworking.get("$url?per_page=100")
                .build()
                .getAsJSONArray(object : JSONArrayRequestListener {

                    override fun onResponse(response: JSONArray?) {
                        val result = response.let { it } ?: return showError(context, Error.PARSE_RESULT_FAILED)
                        if (result.length() == 0) {
                            showError(context, Error.NO_RESULT)
                            return
                        }
                        val list = MutableList(result.length()) {
                            Repo(result.getJSONObject(it))
                        }
                        completion(list)
                    }

                    override fun onError(anError: ANError?) {
                        if (anError?.errorCode == 404) {
                            showError(context, Error.USER_NOT_FOUND)
                        } else {
                            showError(context, Error.REQUEST_FAILED)
                        }
                    }
                })
    }

    private fun showError(context: Context, error: Error) {
        Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show()
    }


    // getter setter
    val url: String
        get() { return baseUrl }

    private var userUrl: String
        get() {
            return baseUrl + "/user"
        }
        set(value) { print("This is been set to $value") }
}