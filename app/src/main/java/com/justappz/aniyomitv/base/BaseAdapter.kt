package com.justappz.aniyomitv.base

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

open class BaseRecyclerViewAdapter<T, VB : ViewBinding>(
    private var items: List<T> = listOf(),
    private val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> VB,
    private val bind: VB.(T, Int) -> Unit
) : RecyclerView.Adapter<BaseRecyclerViewAdapter.BaseViewHolder<VB>>() {

    var onItemClick: ((T, Int) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newItems: List<T>) {
        items = newItems
        notifyDataSetChanged()
    }

    fun getItem(position: Int): T = items[position]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<VB> {
        val binding = bindingInflater(LayoutInflater.from(parent.context), parent, false)
        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<VB>, position: Int) {
        holder.binding.bind(items[position], position)
        holder.binding.root.setOnClickListener {
            onItemClick?.invoke(items[position], position)
        }
    }

    override fun getItemCount(): Int = items.size

    class BaseViewHolder<VB : ViewBinding>(val binding: VB) : RecyclerView.ViewHolder(binding.root)
}