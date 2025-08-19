package com.justappz.aniyomitv.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import com.justappz.aniyomitv.R

object ToastUtils {

    @SuppressLint("InflateParams")
    @Suppress("DEPRECATION") // Using custom view for Toast is deprecated in Android 14, but still works
    fun showToast(message: String, activity: Activity) {
        val inflater = activity.layoutInflater
        val layout = inflater.inflate(R.layout.custom_toast, null)
        val textView = layout.findViewById<TextView>(R.id.toast_text)
        textView.text = message

        val toast = Toast(activity)
        toast.duration = Toast.LENGTH_SHORT
        toast.view = layout
        toast.show()
    }
}