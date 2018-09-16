package com.example.leo.githook.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.androidnetworking.AndroidNetworking
import com.example.leo.githook.R
import com.example.leo.githook.Managers.requestManager

class MainActivity : AppCompatActivity() {

    private lateinit var inputRepoName: EditText

    private val username: String
        get() { return inputRepoName.text.toString() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputRepoName = findViewById(R.id.input_repo_name)

        val repoTitleField = findViewById<TextView>(R.id.repo_name)
        repoTitleField?.text = ""

        val button = findViewById<Button>(R.id.btn_confirm)
        button.setOnClickListener {
            requestManager.loadUser(this, username) { user ->
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra(Keys.user, user)
                startActivity(intent)
            }

//            requestManager.loadRepos(inputRepoName.text.toString(), completion = {
//                val intent = Intent(this, ResultActivity::class.java)
////                intent.putExtra("data", it)
//
////                Toast.makeText(this, "Total repos: ${it.length()}", Toast.LENGTH_SHORT).show()
//            })
        }

        AndroidNetworking.initialize(applicationContext)
    }
}

class Keys {

    companion object {
        const val user = "user"
        const val repos = "repos"
    }
}
