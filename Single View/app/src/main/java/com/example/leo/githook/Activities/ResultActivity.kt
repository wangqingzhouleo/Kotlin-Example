package com.example.leo.githook.Activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.leo.githook.Models.User
import com.example.leo.githook.R
import com.example.leo.githook.Managers.requestManager

class ResultActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val user = intent.getParcelableExtra<User>(Keys.user)

        val usernameTextView: TextView = findViewById(R.id.result_username)
        usernameTextView.text = user.name

        val userIdTextView: TextView = findViewById(R.id.result_user_id)
        userIdTextView.text = "User ID: ${user.id}"

        val publicRepoCountTextView: TextView = findViewById(R.id.result_public_repo_count)
        publicRepoCountTextView.text = "Total repos: ${user.publicRepoCount}"
        publicRepoCountTextView.setOnClickListener {
            requestManager.loadReposWithUrl(this, user.reposUrl) { repoList ->
                val intent = Intent(this, RepoListActivity::class.java)
                intent.putParcelableArrayListExtra(Keys.repos, ArrayList(repoList))
                startActivity(intent)
            }
        }

        Glide.with(this).load(user.avatarUrl).into(findViewById(R.id.result_user_avatar))
    }

    fun onUserIdClick(v: View) {
    }
}