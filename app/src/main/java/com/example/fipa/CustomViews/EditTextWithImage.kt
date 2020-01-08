package com.example.fipa.CustomViews

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.fipa.R

/**
 * Created by KING JINHO on 2020-01-08
 */
class EditTextWithImage(context: Context, attrs: AttributeSet) : View(context, attrs) {

    lateinit var mBaseLayout: LinearLayout
    lateinit var mImageView: ImageView
    lateinit var mEditText: EditText

    init {
        setImageView()
        setEditText()
        mBaseLayout = LinearLayout(context).apply {
            orientation = LinearLayout.HORIZONTAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        }
    }

    fun setImageView(){
        val width: Int = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24f, resources.displayMetrics) as Int
        val height:Int = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24f, resources.displayMetrics) as Int
        mImageView = ImageView(context).apply {
            setBackgroundResource(R.drawable.ic_email)
            layoutParams = LinearLayout.LayoutParams(width, height)
        }
    }

    fun setEditText() {
        mEditText = EditText(context).apply {
            layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
        }
    }
}