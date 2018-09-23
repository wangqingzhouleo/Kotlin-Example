package com.example.leo.gitapp.Activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.leo.gitapp.Model.GitAppError
import com.example.leo.gitapp.Model.Keys
import com.example.leo.gitapp.Model.ResponseType
import com.example.leo.gitapp.R
import com.example.leo.gitapp.RequestManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_repo_detail.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setButtonListener()
    }

    private fun setButtonListener() {
        btn_confirm.setOnClickListener {
            val username = input_username.text.toString()
            if (username.isEmpty()) {
                showError(GitAppError.INPUT_ERROR)
            } else {
                RequestManager.shared.loadReposWithUsername(username) { result ->
                    when (result.responseType) {
                        ResponseType.SUCCESS -> {
                            val intent = Intent(this, RepoListActivity::class.java)
                            intent.putParcelableArrayListExtra(Keys.REPOS.rawValue, ArrayList(result.value))
                            startActivity(intent)
                        }
                        ResponseType.ERROR -> showError(result.gitAppError!!)
                    }
                }
            }
        }
    }

    private fun showError(e: GitAppError) {
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show()
    }
}