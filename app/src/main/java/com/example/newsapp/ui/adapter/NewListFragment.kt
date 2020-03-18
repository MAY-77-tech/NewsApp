package com.example.newsapp.ui.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.newsapp.R
import com.example.newsapp.viewmoel.ArticleViewModel
import kotlinx.android.synthetic.main.fragment_new_list.*

/**
 * A simple [Fragment] subclass.
 */
class NewListFragment : Fragment() {

    lateinit var newsAdapter: NewsAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var articleViewModel: ArticleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewManager = LinearLayoutManager(activity)
        newsAdapter = NewsAdapter()
        recyclerNews.apply {
            adapter = newsAdapter
            layoutManager = viewManager
            observeViewModel()
        }

    }

    //data form ViewModel
    fun observeViewModel(){
        articleViewModel = ViewModelProvider(this)
            .get(ArticleViewModel::class.java)

        articleViewModel.getResult().observe(
            viewLifecycleOwner, Observer { result ->
                newsAdapter.updateList(result.articles)
            }
        )
    }

    override fun onResume() {
        super.onResume()
        articleViewModel.loadResult("entertainment")
    }

}
