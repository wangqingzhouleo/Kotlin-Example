package com.example.leo.githook.View

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
//import android.support.v7.widget.RecyclerView.Adapter
import android.view.View
import android.view.ViewGroup
import com.example.leo.githook.Models.Repo
import com.example.leo.githook.R
import kotlinx.android.synthetic.main.adapter_repo_item.view.*

class RepoItemAdapter(private val repoList: List<Repo>,
                      private val onRepoClick: (Repo) -> Unit): RecyclerView.Adapter<RepoItemAdapter.RepoItemVH>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RepoItemVH {
        return RepoItemVH(LayoutInflater.from(parent?.context).inflate(R.layout.adapter_repo_item, parent, false))
    }

    override fun getItemCount(): Int {
        return repoList.count()
    }

    override fun onBindViewHolder(holder: RepoItemVH?, position: Int) {
        holder?.bind(repoList[position], onRepoClick)
    }

    class RepoItemVH(val view: View): RecyclerView.ViewHolder(view) {

        fun bind(repo: Repo, onClick: (Repo) -> Unit) {
            view.view_repo_name.text = repo.name
            view.setOnClickListener { onClick(repo) }
        }
    }
}