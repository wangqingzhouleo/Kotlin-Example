package com.example.leo.gitapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.leo.gitapp.Model.Keys
import com.example.leo.gitapp.Model.Repo
import com.example.leo.gitapp.dummy.DummyContent
import kotlinx.android.synthetic.main.activity_repo_detail.*
import kotlinx.android.synthetic.main.repo_detail.view.*

/**
 * A fragment representing a single Repo detail screen.
 * This fragment is either contained in a [RepoListActivity]
 * in two-pane mode (on tablets) or a [RepoDetailActivity]
 * on handsets.
 */
class RepoDetailFragment : Fragment() {

    /**
     * The dummy content this fragment is presenting.
     */
    private var item: Repo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(Keys.REPO.rawValue)) {
                // Load the dummy content specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load content from a content provider.
                item = it.getParcelable(Keys.REPO.rawValue)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.repo_detail, container, false)

        // Show the dummy content as text in a TextView.
        item?.let { repo ->
            rootView.repo_id.text = "ID: ${repo.id}"
            rootView.repo_name.text = "Repo name: ${repo.name}"
            rootView.repo_html_link.text = "URL: ${repo.htmlUrl}"
            rootView.repo_language.text = "Language: ${repo.language}"

            rootView.repo_html_link.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(repo.htmlUrl)))
            }
        }

        return rootView
    }
}
