package com.example.leo.gitapp.Activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.example.leo.gitapp.Model.Keys
import com.example.leo.gitapp.R
import com.example.leo.gitapp.RepoDetailFragment
import kotlinx.android.synthetic.main.activity_repo_detail.*

/**
 * An activity representing a single Repo detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a [RepoListActivity].
 */
class RepoDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_detail)

        // Show the Up button in the action bar.
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            val fragment = RepoDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(Keys.REPO.rawValue, intent.getParcelableExtra(Keys.REPO.rawValue))
                }
            }

            supportFragmentManager.beginTransaction()
                    .add(R.id.repo_detail_container, fragment)
                    .commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) =
            when (item.itemId) {
                android.R.id.home -> {
                    // This ID represents the Home or Up button. In the case of this
                    // activity, the Up button is shown. For
                    // more details, see the Navigation pattern on Android Design:
                    //
                    // http://developer.android.com/design/patterns/navigation.html#up-vs-back

                    navigateUpTo(Intent(this, RepoListActivity::class.java))
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }
}
