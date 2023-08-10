package com.mustafaunlu.ecommerce.utils

import android.text.Editable
import android.view.View
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.textfield.TextInputEditText
import com.mustafaunlu.ecommerce.R
import com.mustafaunlu.ecommerce.ui.contract.AbstractTextWatcher
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.onStart

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.showToast(text: String) = Toast.makeText(this.context, text, Toast.LENGTH_SHORT).show()

fun BottomNavigationView.updateCartBadgeVisibility(showBadge: Boolean) {
    val badge = getOrCreateBadge(R.id.cartFragment)
    badge.isVisible = showBadge
}

fun TextInputEditText.observeTextChanges(): Flow<String> {
    return callbackFlow {
        val textWatcher = object : AbstractTextWatcher() {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                super.onTextChanged(s, start, before, count)
                trySend(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {
                super.afterTextChanged(s)
                trySend(s.toString())
            }
        }
        addTextChangedListener(textWatcher)

        awaitClose {
            removeTextChangedListener(textWatcher)
        }
    }.onStart {
        emit(text.toString())
    }
}
