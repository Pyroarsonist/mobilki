package com.gmail.velikiydan.mobile.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.gmail.velikiydan.mobile.R
import com.gmail.velikiydan.mobile.data.dtos.FlowerDto
import com.gmail.velikiydan.mobile.ui.activities.MainActivity
import com.gmail.velikiydan.mobile.utils.Constant
import kotlinx.android.synthetic.main.fragment_result.*


class ResultFragment : Fragment() {

    private lateinit var data: FlowerDto

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.getParcelable<FlowerDto>(Constant.FLOWER_DTO_ARG)
            ?.let { flowerData ->
                this.data = flowerData
            } ?: throw IllegalStateException("FlowerData not found!")

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateFlowerData(data)
    }

    private fun updateFlowerData(flowerData: FlowerDto) {
        priceMinView.text = flowerData.minPrice.toString()
        priceMaxView.text = flowerData.maxPrice.toString()
        currentFlowersView.text = flowerData.name
        currentColorView.setBackgroundColor(flowerData.color)
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

        fun newInstance(data: FlowerDto): ResultFragment {
            return ResultFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(Constant.FLOWER_DTO_ARG, data)
                }
            }
        }

    }
}
