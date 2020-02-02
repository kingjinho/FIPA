package com.example.fipa.views

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.fipa.R
import kotlinx.android.synthetic.main.textview_with_button_all.view.*

/**
 * Created by KING JINHO on 2020-01-29
 */
class TextViewWithButton(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    lateinit var buttonClickListener: OnButtonClickListener
    var typedArray: TypedArray


    init {
        LayoutInflater.from(context).inflate(R.layout.textview_with_button_all, this)
        typedArray =
            context.theme.obtainStyledAttributes(attrs, R.styleable.TextViewWithButton, 0, 0)
        setSectionHeader()

        if (typedArray.getBoolean(R.styleable.TextViewWithButton_setBorder, true))
            background = resources.getDrawable(R.drawable.all_border_bottom_white, null)

        btnSection.setOnClickListener { buttonClickListener.onClick() }
    }

    private fun setSectionHeader() {
        if (typedArray.getBoolean(R.styleable.TextViewWithButton_setSectionHeaderVisible, true)){
            tvSectionHeader.visibility   = View.VISIBLE
            tvSectionHeader.text =
                typedArray.getString(R.styleable.TextViewWithButton_sectionHeaderText)
        } else
            tvSectionHeader.visibility   = View.INVISIBLE
    }

    fun setOnButtonClickListener(listener: OnButtonClickListener) {
        buttonClickListener = listener
    }

    fun setSectionText(section: String) {
        tvSection.text = section
    }

    interface OnButtonClickListener {
        fun onClick()
    }

}