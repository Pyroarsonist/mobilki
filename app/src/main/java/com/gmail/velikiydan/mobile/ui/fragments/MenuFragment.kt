package com.gmail.velikiydan.mobile.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gmail.velikiydan.mobile.ui.activities.MainActivity
import com.gmail.velikiydan.mobile.R
import com.gmail.velikiydan.mobile.data.dtos.FlowerDto
import com.gmail.velikiydan.mobile.data.entities.FlowerEntity
import com.gmail.velikiydan.mobile.ui.activities.FlowerListActivity
import com.gmail.velikiydan.mobile.utils.Constant
import com.jaygoo.widget.OnRangeChangedListener
import com.jaygoo.widget.RangeSeekBar
import kotlinx.android.synthetic.main.fragment_menu.*
import yuku.ambilwarna.AmbilWarnaDialog


class MenuFragment : Fragment() {
    private lateinit var data: FlowerDto


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        data = FlowerDto()
        arguments?.getParcelable<FlowerDto>(Constant.FLOWER_DTO_ARG)
            ?.let { flowerData ->
                this.data = flowerData
            } ?: throw IllegalStateException("FlowerDto not found!")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateFlowerData(data)
        createResultFragmentOnOkClick(view.context as MainActivity)
        createFlowerListActivityOnListClick(view.context as MainActivity)
        initColorPicker()
        setFlowersOnClick()
        setRangeSeekBarListener()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    private fun updateFlowerData(flowerData: FlowerDto) {
        priceRangeSeekBar.setRange(data.minPrice, flowerData.maxPrice)
        editFlowers.setText(flowerData.name)
        priceRangeSeekBar.setRange(FlowerDto.DEFAULT_MIN_PRICE, FlowerDto.DEFAULT_MAX_PRICE)
        priceRangeSeekBar.setProgress(flowerData.minPrice, flowerData.maxPrice)
    }

    private fun createResultFragmentOnOkClick(context: MainActivity) {
        okButton.setOnClickListener {
            context.flowerViewModel.insert(
                FlowerEntity(
                    null,
                    data.name,
                    data.minPrice,
                    data.maxPrice,
                    data.color
                )
            )
            context.replaceFragment(
                ResultFragment.newInstance(
                    data
                )
            )
        }
    }


    private fun createFlowerListActivityOnListClick(context: MainActivity) {
        listButton.setOnClickListener {
            val intent = Intent(context, FlowerListActivity::class.java)
            startActivity(intent)
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
                    }


                    override fun onCancel(dialog: AmbilWarnaDialog) {
                        // Intentionally empty.
                    }
                })

        colorPickerBtn.setOnClickListener {
            colorPicker.show()
        }
    }

    private fun setFlowersOnClick() {
        editFlowers.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                // Intentionally empty.
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Intentionally empty.
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                data.name = s.toString()
            }
        })
    }

    private fun round(num: Float): Float {
        return ("%.2f".format(num)).toFloat()
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
                data.minPrice = round(leftValue)
                data.maxPrice = round(rightValue)

            }

            override fun onStopTrackingTouch(view: RangeSeekBar?, isLeft: Boolean) {
                // Intentionally empty.
            }

        })
    }

    companion object {
        fun newInstance(data: FlowerDto): MenuFragment {
            return MenuFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(Constant.FLOWER_DTO_ARG, data)
                }
            }
        }
    }
}
