package com.norhan.linkdevelopment.utils.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
    protected var mOnViewClickListener: OnViewClickListener? = null

    init {
        itemView.setOnClickListener(this)
    }

    abstract fun setData(data: T)

    override fun onClick(view: View) {
        if (mOnViewClickListener != null) {
            mOnViewClickListener!!.onViewClick(view, this.adapterPosition)
        }
    }

    fun setOnItemClickListener(listener: OnViewClickListener) {
        this.mOnViewClickListener = listener
    }

    interface OnViewClickListener {
        fun onViewClick(view: View, position: Int)
    }
}
