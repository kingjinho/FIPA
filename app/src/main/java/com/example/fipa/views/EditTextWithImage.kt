package com.example.fipa.views

import android.content.Context
import android.text.InputType

import android.util.AttributeSet
import android.view.Gravity
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.annotation.NonNull
import androidx.core.content.ContextCompat
import com.example.fipa.R

/**
 * Created by KING JINHO on 2020-01-08
 */
class EditTextWithImage(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    private lateinit var mImageView: ImageView
    private lateinit var mEditText: EditText
    private val mMargin: Int = resources.getDimensionPixelSize(R.dimen.all_16dp)

    init {
        orientation = HORIZONTAL
        layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT
        )
        //todo : .apply { setMargins(20,20,20,20) } why not working?
        background = resources.getDrawable(R.drawable.all_border_bottom_white, null)
        //setBackgroundResource(R.drawable.all_border_bottom_white)
        context.theme.obtainStyledAttributes(attrs, R.styleable.EditTextWithImage, 0, 0).apply {
            try {
                setImageView(
                    getResourceId(R.styleable.EditTextWithImage_image, 0),
                    getInt(R.styleable.EditTextWithImage_imagePosition, 0)
                )
                setEditText(
                    getString(R.styleable.EditTextWithImage_placeholder),
                    getBoolean(R.styleable.EditTextWithImage_confidential, false),
                    getDimensionPixelSize(R.styleable.EditTextWithImage_textSize, 10).toFloat()
                )
                when (getInt(R.styleable.EditTextWithImage_imagePosition, 0)) {
                    0 -> {
                        addView(mImageView)
                        addView(mEditText)
                    }
                    else -> {
                        addView(mEditText)
                        addView(mImageView)
                    }
                }
            } finally {
                recycle()
                invalidate()
                requestLayout()
            }
        }
    }


    private fun setImageView(imageRes: Int, leftOrRight: Int) {
        mImageView = ImageView(context).apply {
            layoutParams = LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(
                    when (leftOrRight) {
                        0 -> 0
                        else -> mMargin
                    }, 0, when (leftOrRight) {
                        0 -> mMargin
                        else -> 0
                    }, 0
                )
            }
            setVerticalGravity(Gravity.CENTER_VERTICAL)
            setBackgroundResource(imageRes)
        }
        //invalidate()
        //requestLayout()
    }

    private fun setEditText(placeholder: String?, isConfidential: Boolean, textSize: Float) {
        mEditText = EditText(context).apply {
            layoutParams = LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT
            )
            setSingleLine()
            setTextColor(ContextCompat.getColor(context, R.color.primaryTextColor))
            setHintTextColor(ContextCompat.getColor(context, R.color.primaryTextColor))
            setBackgroundResource(android.R.color.transparent)
            setTextSize(textSize)
            hint = placeholder
        }
        mEditText.inputType
        mEditText.inputType =  when (isConfidential) {
            true -> InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            else -> InputType.TYPE_CLASS_TEXT
        }
       // invalidate()
      //  requestLayout()
    }

    fun getText(): String {
        return mEditText.text.toString()
    }

    fun setText( text: String?): Unit {
        mEditText.setText(text)
    }

    fun getEditText(): EditText {
        return mEditText
    }

}