package com.example.fipa.views

import android.content.Context
import android.content.res.TypedArray
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import com.example.fipa.R
import kotlinx.android.synthetic.main.textview_with_button_all.view.*

/**
 * Created by KING JINHO on 2020-01-29
 */
class TextViewWithButton(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    lateinit var buttonClickListener: OnButtonClickListener
    private var mTypedArray: TypedArray
    private lateinit var mBindingUtil: DataBindingUtil


    init {
        var inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)
        mBindingUtil = DataBindingUtil.inflate(inflater as LayoutInflater, R.layout.textview_with_button_all, this, false)
        //LayoutInflater.from(context).inflate(R.layout.textview_with_button_all, this)
        mTypedArray =
            context.theme.obtainStyledAttributes(attrs, R.styleable.TextViewWithButton, 0, 0)
        setSectionHeader()
        setSectionText(null)
        setTextColor()

        if (mTypedArray.getBoolean(R.styleable.TextViewWithButton_setBorder, true))
            background = resources.getDrawable(R.drawable.all_border_bottom_white, null)

        btnSection.setOnClickListener { buttonClickListener.onClick() }
    }

    private fun setSectionHeader() {
        if (mTypedArray.getBoolean(R.styleable.TextViewWithButton_setSectionHeaderVisible, true)) {
            tvSectionHeader.visibility = View.VISIBLE
            tvSectionHeader.text =
                mTypedArray.getString(R.styleable.TextViewWithButton_sectionHeaderText)
        } else
            tvSectionHeader.visibility = View.INVISIBLE
    }

    fun getSectionHeaderText(): String {
        return tvSectionHeader.text.toString()
    }

    fun setOnButtonClickListener(listener: OnButtonClickListener) {
        buttonClickListener = listener
    }

    fun setSectionText(section: String?) {
        tvSection.text = section ?: resources.getString(R.string.all_choose)
    }

    fun getSectionText(): String{
        return tvSection.text.toString()
    }


    private fun setTextColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            tvSection.setTextColor(resources.getColor(R.color.primaryTextColor, null))
        else
            tvSection.setTextColor(resources.getColor(R.color.primaryTextColor))
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            tvSection.setTextColor(resources.getColor(R.color.primaryTextColor, null))
        else
            tvSection.setTextColor(resources.getColor(R.color.primaryTextColor))*/
    }

    interface OnButtonClickListener {
        fun onClick()
    }

}