package com.gmail.velikiydan.mobile.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import com.gmail.velikiydan.mobile.MainActivity
import com.gmail.velikiydan.mobile.R


class ResultFragment : Fragment() {
    private lateinit var data: FlowerData

    private lateinit var textViewPriceMin: TextView
    private lateinit var textViewPriceMax: TextView
    private lateinit var textViewCurrentFlowers: TextView
    private lateinit var viewCurrentColor: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            data = FlowerData(
                it.getFloat("minPrice"),
                it.getFloat("maxPrice"),
                it.getInt("color"),
                it.getString("flowerText", "")
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup(view)
        setValues()
    }

    private fun setup(v: View) {
        textViewPriceMin = v.findViewById(R.id.priceMin)
        textViewPriceMax = v.findViewById(R.id.priceMax)
        textViewCurrentFlowers = v.findViewById(R.id.currentFlowers)
        viewCurrentColor = v.findViewById(R.id.currentColor)
    }

    private fun setValues() {
        textViewPriceMin.text = String.format("%.2f", data.minPrice)
        textViewPriceMax.text = String.format("%.2f", data.maxPrice)
        textViewCurrentFlowers.text = data.flowerText
        viewCurrentColor.setBackgroundColor(data.color)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val ctx = context as MainActivity
                ctx.replaceFragment(MenuFragment.newInstance(data))
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }


    companion object {
        fun newInstance(data: FlowerData) =
            ResultFragment().apply {
                arguments = Bundle().apply {
                    putFloat("minPrice", data.minPrice)
                    putFloat("maxPrice", data.maxPrice)
                    putInt("color", data.color)
                    putString("flowerText", data.flowerText)
                }
            }
    }
}
