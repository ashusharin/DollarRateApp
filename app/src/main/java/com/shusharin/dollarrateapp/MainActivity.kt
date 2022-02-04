package com.shusharin.dollarrateapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.shusharin.dollarrateapp.core.RateApp
import com.shusharin.dollarrateapp.ui.MainViewModel
import com.shusharin.dollarrateapp.ui.RateAdapter

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    lateinit var adapter: RateAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = (application as RateApp).mainViewModel
        adapter = RateAdapter(object : RateAdapter.Retry {
            override fun tryAgain() {
                viewModel.fetchRates()
            }
        })
        setupRecyclerView()

        viewModel.observe(this, {
            adapter.update(it)
        })
        viewModel.fetchRates()
    }

    private fun setupRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

    }
}