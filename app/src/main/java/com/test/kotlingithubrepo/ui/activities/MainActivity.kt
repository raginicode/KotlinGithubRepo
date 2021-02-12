package com.test.kotlingithubrepo.ui.activities



import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.kotlingithubrepo.R
import com.test.kotlingithubrepo.data.models.Item
import com.test.kotlingithubrepo.data.models.Repo
import com.test.kotlingithubrepo.data.remote.APIService
import com.test.kotlingithubrepo.data.remote.ApiUtils
import com.test.kotlingithubrepo.ui.viewholders.RepositoryItemViewHolder
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {


    var context: Context? = null
    private var mAPIService: APIService? = null
    var repositoryList: MutableList<Item> = arrayListOf()
    lateinit  var adapter: RepositoryItemViewHolder
    var page = 1
    private var layoutManager: LinearLayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        context = this

        supportActionBar?.title = "Trending Android Repos"

        mAPIService = ApiUtils.apiService

        setupRecyclerView()

        fetchRepository()
    }

    /// Make call to fetch trending repositories
    private fun fetchRepository() {


        mAPIService?.getRepositories(page)?.enqueue(object : Callback<Repo> {
            override fun onFailure(call: Call<Repo>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<Repo>?, response: Response<Repo>?) {
                if (response != null) {

                    if (response.isSuccessful){

                        val repo = response.body()

                        repositoryList.addAll(repo?.items!!)

                        adapter.repositories = repositoryList

                        adapter.notifyDataSetChanged()

                    }
                }
            }

        })
    }



    private fun setupRecyclerView() {
        layoutManager = LinearLayoutManager(context)

        recyclerView.layoutManager = layoutManager

        adapter  = RepositoryItemViewHolder(repositoryList)

        recyclerView.adapter = adapter

        setupRecyclerListener()
    }



    private val lastVisibleItemPosition: Int
        get() = layoutManager!!.findLastVisibleItemPosition()



    private fun setupRecyclerListener() {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val totalItemCount = recyclerView.layoutManager?.itemCount
                if (totalItemCount == lastVisibleItemPosition + 1) {
                    page += 1
                    fetchRepository()
                }
            }
        })
    }
}
