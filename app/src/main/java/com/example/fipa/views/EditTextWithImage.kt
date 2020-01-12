package com.example.fipa.views

import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.annotation.NonNull
import androidx.core.content.ContextCompat
import androidx.core.content.res.getIntOrThrow
import com.example.fipa.R

/**
 * Created by KING JINHO on 2020-01-08
 */
class EditTextWithImage(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    lateinit var mImageView: ImageView
    lateinit var mEditText: EditText

    init {
        orientation = HORIZONTAL
        layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT
        )

        context.theme.obtainStyledAttributes(attrs, R.styleable.EditTextWithImage, 0, 0).apply {
            try {
                setImageView(getResourceId(R.styleable.EditTextWithImage_image, 0), getInt(R.styleable.EditTextWithImage_imagePosition, 0))
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


    fun setImageView(imageRes: Int, leftOrRight: Int) {
        val margin: Int = resources.getDimensionPixelSize(R.dimen.margin_16dp)
        mImageView = ImageView(context).apply {
            layoutParams = LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT
            ).apply { setMargins(when(leftOrRight) { 0 -> 0 else -> margin}, 0, when(leftOrRight) { 0 -> margin else -> 0}, 0) }
            setVerticalGravity(Gravity.CENTER_VERTICAL)
            setBackgroundResource(imageRes)
        }
        invalidate()
        requestLayout()

    }

    fun setEditText(placeholder: String?, isConfidential: Boolean, textSize: Float) {
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
            inputType = when (isConfidential) {
                true -> InputType.TYPE_TEXT_VARIATION_PASSWORD
                else -> InputType.TYPE_CLASS_TEXT
            }
        }
        invalidate()
        requestLayout()
    }

    fun getText(): String {
        return mEditText.text.toString()
    }

    fun setText(@NonNull text: String): Unit {
        mEditText.setText(text)
    }
}