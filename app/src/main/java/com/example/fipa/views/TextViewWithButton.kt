package com.example.fipa.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.fipa.R

/**
 * Created by KING JINHO on 2020-01-29
 */
class TextViewWithButton(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    init {
        var layout = LayoutInflater.from(context).inflate(R.layout.textview_with_button_all, this)
        var tvSection = findViewById<TextView>(R.id.tvSection)
        var btnSection = findViewById<Button>(R.id.btnSection)
    }
}