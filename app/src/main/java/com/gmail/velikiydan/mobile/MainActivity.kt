package com.gmail.velikiydan.mobile

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import yuku.ambilwarna.AmbilWarnaDialog
import org.florescu.android.rangeseekbar.RangeSeekBar

const val DEFAULT_COLOR = Color.RED


class MainActivity : AppCompatActivity() {

    private lateinit var etFlowers: EditText
    private lateinit var tv: TextView
    private lateinit var currentColor: View
    private lateinit var cpBtn: Button
    private lateinit var okBtn: Button
    private lateinit var pMinTextView: TextView
    private lateinit var pMaxTextView: TextView
    private lateinit var l: TextView
    private lateinit var l2: TextView
    private lateinit var priceRangeSeekBar: RangeSeekBar<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setup()
        makeViewsVisibleOnClick()
        initColorPicker()
        setFlowersOnClick()
        setRangeSeekBarListener()

    }

    private fun setup() {
        etFlowers = findViewById<EditText>(R.id.editFlowers)
        tv = findViewById<TextView>(R.id.currentFlowers)
        currentColor = findViewById<View>(R.id.currentColor)
        cpBtn = findViewById<Button>(R.id.colorPickerBtn)
        okBtn = findViewById<Button>(R.id.ok)
        pMinTextView = findViewById<TextView>(R.id.priceMin)
        pMaxTextView = findViewById<TextView>(R.id.priceMax)

        l = findViewById<TextView>(R.id._currentColorLabel)
        l2 = findViewById<TextView>(R.id._currentPriceLabel)

        priceRangeSeekBar = findViewById<RangeSeekBar<*>>(R.id.priceRangeSeekBar)


        currentColor.setBackgroundColor(DEFAULT_COLOR)


    }

    private fun makeViewsVisibleOnClick() {
        okBtn.setOnClickListener {
            listOf(tv, l, l2, currentColor, pMinTextView, pMaxTextView).forEach {
                it.visibility = View.VISIBLE
            }
        }
    }

    private fun initColorPicker() {
        val colorPicker =
                AmbilWarnaDialog(this, DEFAULT_COLOR, object : AmbilWarnaDialog.OnAmbilWarnaListener {
                    override fun onOk(dialog: AmbilWarnaDialog, color: Int) {
                        currentColor.setBackgroundColor(color)
                        return
                    }


                    override fun onCancel(dialog: AmbilWarnaDialog) {
                        return
                    }
                })

        cpBtn.setOnClickListener {
            colorPicker.show()
        }
    }

    private fun setFlowersOnClick() {
        etFlowers.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(
                    s: CharSequence?, start: Int, before: Int, count: Int
            ) {
                tv.text = s
            }
        })
    }

    private fun setRangeSeekBarListener() {
        priceRangeSeekBar.setOnRangeSeekBarChangeListener { _, minValue, maxValue ->
            run {
                pMinTextView.text =
                        (priceRangeSeekBar.absoluteMinValue.toInt() - minValue.toInt()).toString()
                pMaxTextView.text =
                        (priceRangeSeekBar.absoluteMinValue.toInt() - maxValue.toInt()).toString()

            }
        }
    }

}