package com.volare.mojikore.ui.mojiDetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.skydoves.bindables.binding
import com.volare.mojikore.R
import com.volare.mojikore.databinding.MojiGridViewBinding

class MojiDetailAdapter :
        RecyclerView.Adapter<MojiDetailAdapter.CustomViewHolder>() {
    private val items: MutableList<String> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val binding =  parent.binding<MojiGridViewBinding>(R.layout.moji_grid_view)

        return CustomViewHolder(binding)
    }

    fun setGridList(list: List<String>) {
        val previousItemSize = items.size
        items.clear()
        items.addAll(list)
        notifyItemRangeChanged(previousItemSize, list.size)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.binding.apply {
            item = items[position]
            executePendingBindings()
        }
    }

    override fun getItemCount() = items.size

    class CustomViewHolder(val binding: MojiGridViewBinding) : RecyclerView.ViewHolder(binding.root)
}
