package com.example.leo.gitapp.Activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.design.widget.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.leo.gitapp.Model.Keys
import com.example.leo.gitapp.Model.Repo
import com.example.leo.gitapp.R
import com.example.leo.gitapp.RepoDetailFragment

import com.example.leo.gitapp.dummy.DummyContent
import kotlinx.android.synthetic.main.activity_repo_list.*
import kotlinx.android.synthetic.main.repo_list_content.view.*
import kotlinx.android.synthetic.main.repo_list.*

/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [RepoDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class RepoListActivity : AppCompatActivity() {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var twoPane: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_list)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        if (repo_detail_container != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            twoPane = true
        }

        setupRecyclerView(repo_list)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        val repos = intent.getParcelableArrayListExtra<Repo>(Keys.REPOS.rawValue)
        recyclerView.adapter = SimpleItemRecyclerViewAdapter(this, repos, twoPane)
    }

    class SimpleItemRecyclerViewAdapter(private val parentActivity: RepoListActivity,
                                        private val values: List<Repo>,
                                        private val twoPane: Boolean) :
            RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

        private val onClickListener: View.OnClickListener

        init {
            onClickListener = View.OnClickListener { v ->
                val item = v.tag as Repo
                if (twoPane) {
                    val fragment = RepoDetailFragment().apply {
                        arguments = Bundle().apply {
                            putParcelable(Keys.REPO.rawValue, item)
                        }
                    }
                    parentActivity.supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.repo_detail_container, fragment)
                            .commit()
                } else {
                    val intent = Intent(v.context, RepoDetailActivity::class.java).apply {
                        putExtra(Keys.REPO.rawValue, item)
                    }
                    v.context.startActivity(intent)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.repo_list_content, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = values[position]
            holder.idView.text = item.name

            with(holder.itemView) {
                tag = item
                setOnClickListener(onClickListener)
            }
        }

        override fun getItemCount() = values.size

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val idView: TextView = view.id_text
            val contentView: TextView = view.content
        }
    }
}
