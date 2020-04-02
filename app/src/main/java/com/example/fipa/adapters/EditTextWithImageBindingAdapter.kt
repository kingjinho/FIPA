package com.example.fipa.adapters

import android.text.Editable
import android.text.TextWatcher
import androidx.core.widget.addTextChangedListener
import androidx.databinding.*
import androidx.databinding.adapters.TextViewBindingAdapter
import com.example.fipa.views.EditTextWithImage

/**
 * Created by KING JINHO on 2020-03-30
 */

object EditTextWithImageBindingAdapter {

    @BindingAdapter("text")
    @JvmStatic
    fun setTextWatcher(view: EditTextWithImage, content : String?) {
        val current = view.getText()
        if (current != content) {
            view.setText(content)
        }
    }

    @BindingAdapter("textAttrChanged")
    @JvmStatic
    fun setInverseBindingListener(view: EditTextWithImage, listener: InverseBindingListener) {
        var watcher = object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                listener?.onChange()
            }
        }
        view.getEditText().addTextChangedListener(watcher)
    }

    @InverseBindingAdapter(attribute = "text", event = "textAttrChanged")
    @JvmStatic
    fun getText(view: EditTextWithImage): String {
        return view.getEditText().text.toString()
    }
}


