package com.test.kotlingithubrepo.ui.viewholders

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.kotlingithubrepo.R
import com.test.kotlingithubrepo.data.Store
import com.test.kotlingithubrepo.data.models.Item
import com.test.kotlingithubrepo.ui.activities.RepoDetailActivity
import kotlinx.android.synthetic.main.repository_item.view.*

class RepositoryItemViewHolder(var repositories: MutableList<Item>) : RecyclerView.Adapter<RepositoryItemViewHolder.ViewHolder>() {

    override fun getItemCount() = repositories.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val repo = repositories.get(position)

        holder.bindRepo(repo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.repository_item, parent, false)

        return ViewHolder(inflatedView)
    }


    class ViewHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {

        private var view: View = v

        private var repo: Item? = null

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View) {

            val context = itemView.context


            val viewRepoIntent = Intent(context, RepoDetailActivity::class.java)

            Store.viewedRepo = repo

            context.startActivity(viewRepoIntent)
        }

        @SuppressLint("SetTextI18n")
        fun bindRepo(repo: Item) {
            this.repo = repo

            Glide.with(itemView.context).load(repo.owner?.avatarUrl).into(view.owner_avatar)

            view.repo_title.text = repo.name

            view.repo_description.text = repo.fullName

            view.repo_watchers.text = " " + String.format("%,d", repo.forksCount)

            view.repo_language.text = repo.language

        }

    }
}