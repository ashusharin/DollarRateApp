package com.shusharin.dollarrateapp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.show_rate -> {
                startDialog()
                true
            }
            else -> false
        }


    private fun setupRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(DividerItemDecoration(this,
            DividerItemDecoration.VERTICAL))

    }

    fun startDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.rate_dialog)
        val etDollar = dialog.findViewById<EditText>(R.id.et_dollar)
        val btn = dialog.findViewById<Button>(R.id.btn_save_price)
        dialog.show()
        etDollar.setText(viewModel.readDollarRate(),TextView.BufferType.EDITABLE)
        btn.setOnClickListener {
            viewModel.saveDollarRate(etDollar.text.toString())
            dialog.dismiss()
        }
    }
}
