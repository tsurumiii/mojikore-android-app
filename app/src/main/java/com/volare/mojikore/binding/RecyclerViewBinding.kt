package com.volare.mojikore.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.skydoves.whatif.whatIfNotNullAs
import com.skydoves.whatif.whatIfNotNullOrEmpty
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
    fun bindAdapterMojiList(view: RecyclerView, list: List<String>?) {
        list.whatIfNotNullOrEmpty { itemList ->
            view.adapter.whatIfNotNullAs<MojiDetailAdapter> { adapter ->
                adapter.setGridList(itemList)
            }
        }
    }
}