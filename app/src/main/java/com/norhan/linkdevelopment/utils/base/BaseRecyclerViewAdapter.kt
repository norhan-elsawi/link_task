package com.norhan.linkdevelopment.utils.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import java.util.*

abstract class BaseRecyclerViewAdapter<T>(private val mOriginalItems: MutableList<T>?) :
    RecyclerView.Adapter<BaseHolder<T>>() {
    private val mOnItemClickListeners = ArrayList<OnRecyclerViewItemClickListener<T>>()

    val items: List<T>?
        get() = mOriginalItems

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<T> {
        val view = LayoutInflater.from(parent.context).inflate(getLayoutId(viewType), parent, false)
        val mHolder = getHolder(view, viewType)
        mHolder.setOnItemClickListener(object : BaseHolder.OnViewClickListener {
            override fun onViewClick(view: View, position: Int) {
                if (!mOnItemClickListeners.isEmpty()) {
                    for (onItemClickListener in mOnItemClickListeners) {
                        if (position != -1)
                            onItemClickListener.onItemClick(view, mOriginalItems!![position], position)
                    }
                }
            }
        })
        return mHolder
    }

    override fun onBindViewHolder(holder: BaseHolder<T>, position: Int) {
        holder.setData(mOriginalItems!![position])
    }

    override fun getItemCount(): Int {
        return mOriginalItems?.size ?: 0
    }

    fun replaceItems(items: List<T>) {
        this.mOriginalItems!!.clear()
        this.mOriginalItems.addAll(items)

        notifyDataSetChanged()
    }

    fun replaceItem(index: Int, item: T) {
        try {
            mOriginalItems!![index] = item
        } catch (e: Exception) {
        }

    }

    fun getItem(position: Int): T? {
        return if (mOriginalItems != null && mOriginalItems.size > position)
            mOriginalItems[position]
        else
            null
    }

    abstract fun getHolder(v: View, viewType: Int): BaseHolder<T>

    @LayoutRes
    abstract fun getLayoutId(viewType: Int): Int

    fun setOnItemClickListener(listener: OnRecyclerViewItemClickListener<T>) {
        this.mOnItemClickListeners.add(listener)
    }

    fun addItems(items: List<T>) {
        mOriginalItems!!.addAll(items)
        notifyDataSetChanged()
    }

    fun clearAllData() {
        mOriginalItems!!.clear()
        notifyDataSetChanged()
    }

    fun removeItemAt(position: Int) {
        val remove = mOriginalItems!!.removeAt(position)
        if (remove != null) notifyItemRemoved(position)
    }

    interface OnRecyclerViewItemClickListener<T> {

        fun onItemClick(view: View, data: T, position: Int)
    }
}
