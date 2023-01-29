package com.akinguldere.yktcase.utils.extensions

import android.view.View
import android.widget.ImageView
import androidx.activity.OnBackPressedDispatcherOwner
import androidx.databinding.BindingAdapter
import com.akinguldere.yktcase.R
import com.bumptech.glide.Glide

@BindingAdapter("urlImage")
fun bindUrlImage(view: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(view).load(url).let { request ->
            request.error(R.drawable.ykt_image).into(view)
        }
    } else {
        // if image has a problem
        view.setImageResource(R.drawable.ykt_image)
    }
}

@BindingAdapter("onBackPressed")
fun bindOnBackPressed(view: View, onBackPress: Boolean) {
    val context = view.context
    if (onBackPress && context is OnBackPressedDispatcherOwner) {
        view.setOnClickListener {
            context.onBackPressedDispatcher.onBackPressed()
        }
    }
}