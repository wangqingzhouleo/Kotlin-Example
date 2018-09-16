package com.example.leo.githook.Activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.leo.githook.Models.Repo
import com.example.leo.githook.R
import com.example.leo.githook.View.RepoItemAdapter
import kotlinx.android.synthetic.main.activity_repo_list.*

class RepoListActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_list)

        val repos = intent.getParcelableArrayListExtra<Repo>(Keys.repos)

        repoListView.layoutManager = LinearLayoutManager(this)
        repoListView.adapter = RepoItemAdapter(repos) { openRepo(it) }
    }

    fun openRepo(repo: Repo) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(repo.htmlUrl)))
    }
}