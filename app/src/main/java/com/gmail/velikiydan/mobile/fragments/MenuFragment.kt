package com.gmail.velikiydan.mobile.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.gmail.velikiydan.mobile.MainActivity
import com.gmail.velikiydan.mobile.R
import com.jaygoo.widget.OnRangeChangedListener
import com.jaygoo.widget.RangeSeekBar
import yuku.ambilwarna.AmbilWarnaDialog


class MenuFragment : Fragment() {
    private lateinit var editFlowers: EditText
    private lateinit var colorPickerBtn: Button
    private lateinit var okBtn: Button
    private lateinit var priceRangeSeekBar: RangeSeekBar

    private lateinit var data: FlowerData


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        data = FlowerData()
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
        createResultFragmentOnOkClick(view.context as MainActivity)
        initColorPicker()
        setFlowersOnClick()
        setRangeSeekBarListener()
    }

    private fun setValues() {
        priceRangeSeekBar.setRange(data.minPrice, data.maxPrice)
        editFlowers.setText(data.flowerText)
        priceRangeSeekBar.setRange(FlowerData.DEFAULT_MIN_PRICE, FlowerData.DEFAULT_MAX_PRICE)
        priceRangeSeekBar.setProgress(data.minPrice, data.maxPrice)
    }

    private fun setup(v: View) {
        editFlowers = v.findViewById(R.id.editFlowers)
        colorPickerBtn = v.findViewById(R.id.colorPickerBtn)
        priceRangeSeekBar = v.findViewById(R.id.priceRangeSeekBar)
        okBtn = v.findViewById(R.id.ok)
    }

    private fun createResultFragmentOnOkClick(context: MainActivity) {
        okBtn.setOnClickListener {
            context.replaceFragment(
                ResultFragment.newInstance(
                    data
                )
            )

        }
    }

    private fun initColorPicker() {
        val colorPicker =
            AmbilWarnaDialog(
                this.context,
                data.color,
                object : AmbilWarnaDialog.OnAmbilWarnaListener {
                    override fun onOk(dialog: AmbilWarnaDialog, color: Int) {
                        data.color = color
                        return
                    }


                    override fun onCancel(dialog: AmbilWarnaDialog) {
                        return
                    }
                })

        colorPickerBtn.setOnClickListener {
            colorPicker.show()
        }
    }

    private fun setFlowersOnClick() {
        editFlowers.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(
                s: CharSequence?, start: Int, before: Int, count: Int
            ) {
                data.flowerText = s.toString()
            }
        })
    }

    private fun setRangeSeekBarListener() {
        priceRangeSeekBar.setOnRangeChangedListener(object : OnRangeChangedListener {
            override fun onStartTrackingTouch(view: RangeSeekBar?, isLeft: Boolean) {
            }

            override fun onRangeChanged(
                view: RangeSeekBar?,
                leftValue: Float,
                rightValue: Float,
                isFromUser: Boolean
            ) {
                data.minPrice = leftValue
                data.maxPrice = rightValue

            }

            override fun onStopTrackingTouch(view: RangeSeekBar?, isLeft: Boolean) {
            }

        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    companion object {
        fun newInstance(data: FlowerData) =
            MenuFragment().apply {
                arguments = Bundle().apply {
                    putFloat("minPrice", data.minPrice)
                    putFloat("maxPrice", data.maxPrice)
                    putInt("color", data.color)
                    putString("flowerText", data.flowerText)
                }
            }
    }
}
