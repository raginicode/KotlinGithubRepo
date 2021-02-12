package com.test.kotlingithubrepo.ui.activities

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.test.kotlingithubrepo.R
import com.test.kotlingithubrepo.data.Store
import com.test.kotlingithubrepo.data.models.Item
import kotlinx.android.synthetic.main.activity_repo_detail.*


class RepoDetailActivity : AppCompatActivity() {

    var repo: Item? = null
    var context:Context?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_repo_detail)

        context = this

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        repo = Store.viewedRepo

        if(repo != null){
            displayRepoInformation()
        }
    }

    private fun displayRepoInformation() {

        supportActionBar?.title = repo?.name
        context?.let { Glide.with(it).load(repo?.owner?.avatarUrl).into(owner_avatar) }


        repo_title.text = repo?.name

        repo_description.text = repo?.fullName

        repo_full_description.text = repo?.description

        repo_watchers.text = String.format("%,d", repo?.watchers)

        repo_stars.text = String.format("%,d", repo?.stargazersCount)

        repo_forks.text = String.format("%,d", repo?.forksCount)

        repo_open_issues.text =  String.format("%,d", repo?.openIssues)

        repo_language.text = repo?.language
    }





    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.getItemId()) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
