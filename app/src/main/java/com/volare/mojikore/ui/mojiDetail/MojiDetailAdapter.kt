package com.volare.mojikore.ui.mojiDetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.volare.mojikore.R

class MojiDetailAdapter(private val customList: Array<String>) :
        RecyclerView.Adapter<MojiDetailAdapter.CustomViewHolder>() {

    class CustomViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val img = item.findViewById<ImageView>(R.id.imageView)
        val txt = item.findViewById<TextView>(R.id.dateTimeValue)
    }

    override fun getItemCount(): Int {
        return customList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.moji_grid_view, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.txt.text = customList[position]
        holder.img.setImageResource(R.mipmap.ic_launcher_round)
    }
}
