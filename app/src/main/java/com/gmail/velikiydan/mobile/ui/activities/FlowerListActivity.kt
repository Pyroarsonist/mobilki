package com.gmail.velikiydan.mobile.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gmail.velikiydan.mobile.R
import com.gmail.velikiydan.mobile.data.viewmodel.FlowerViewModel
import com.gmail.velikiydan.mobile.ui.adapters.FlowerListAdapter
import kotlinx.android.synthetic.main.activity_flower_list.*


class FlowerListActivity : AppCompatActivity() {
    private lateinit var flowerViewModel: FlowerViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flower_list)

        val adapter = FlowerListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        flowerViewModel = ViewModelProvider(this).get(FlowerViewModel::class.java)
        flowerViewModel.allFlowers.observe(this, Observer { flowers ->
            flowers?.let { adapter.setFlowers(it) }
        })


    }


}