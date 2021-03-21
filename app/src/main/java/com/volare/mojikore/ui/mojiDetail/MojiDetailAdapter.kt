package com.volare.mojikore.ui.mojiDetail

import android.app.AlertDialog
import android.os.SystemClock
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import com.skydoves.bindables.binding
import com.skydoves.bindables.bindingProperty
import com.volare.mojikore.R
import com.volare.mojikore.databinding.MojiGridViewBinding
import com.volare.mojikore.model.Image

class MojiDetailAdapter :
        RecyclerView.Adapter<MojiDetailAdapter.CustomViewHolder>(){
    private val items: MutableList<Image> = mutableListOf()
    private var onClickedAt = 0L

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val binding = parent.binding<MojiGridViewBinding>(R.layout.moji_grid_view)
        binding.cardView.setOnClickListener {
            Log.v("MojiDetailAdapter", "${binding.item}")
            val action = MojiDetailFragmentDirections.actionMojiDetailFragmentToMojiDetailDialogFragment(binding.item!!.imgUrl)
            it.findNavController().navigate(action)
        }
        return CustomViewHolder(binding)
    }

    fun setGridList(list: List<Image>) {
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
