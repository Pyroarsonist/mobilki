package com.gmail.velikiydan.mobile.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.gmail.velikiydan.mobile.R
import com.gmail.velikiydan.mobile.data.dtos.FlowerDto
import com.gmail.velikiydan.mobile.data.viewmodel.FlowerViewModel
import com.gmail.velikiydan.mobile.ui.fragments.MenuFragment


class MainActivity : AppCompatActivity() {
    lateinit var flowerViewModel: FlowerViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        flowerViewModel = ViewModelProvider(this).get(FlowerViewModel::class.java)

        replaceFragment(MenuFragment.newInstance(FlowerDto()))
    }

    fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction =
            supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment, fragment)
        fragmentTransaction.commit()
    }


}