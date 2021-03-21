package com.volare.mojikore.binding

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.skydoves.whatif.whatIfNotNullAs
import com.skydoves.whatif.whatIfNotNullOrEmpty
import com.volare.mojikore.R
import com.volare.mojikore.model.Image
import com.volare.mojikore.ui.mojiDetail.MojiDetailAdapter

object RecyclerViewBinding {

    @JvmStatic
    @BindingAdapter("adapter")
    fun bindAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
        view.adapter = adapter.apply {
            stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        }
    }

    @JvmStatic
    @BindingAdapter("adapterMojiList")
    fun bindAdapterMojiList(view: RecyclerView, list: List<Image>?) {
        list.whatIfNotNullOrEmpty { itemList ->
            view.adapter.whatIfNotNullAs<MojiDetailAdapter> { adapter ->
                adapter.setGridList(itemList)
            }
        }
    }

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun bindImage(imgView: ImageView, imgUrl: String?) {
        imgUrl?.let {
            val imgUri = imgUrl.toUri().buildUpon().build()
            Glide.with(imgView.context)
                    .load(imgUri)
                    .into(imgView)
        }
    }
}