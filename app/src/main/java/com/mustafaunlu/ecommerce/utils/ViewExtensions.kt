package com.mustafaunlu.ecommerce.utils

import android.view.View
import android.widget.Toast

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.showToast(text: String) = Toast.makeText(this.context, text, Toast.LENGTH_SHORT).show()
