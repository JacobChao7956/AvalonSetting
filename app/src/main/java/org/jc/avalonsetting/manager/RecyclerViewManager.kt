package org.jc.avalonsetting.manager

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * 初始化直向列表
 */
fun RecyclerView.initVerticalList(context: Context, adapter: RecyclerView.Adapter<*>) {
    layoutManager = LinearLayoutManager(context)
    hasFixedSize()
    this.adapter = adapter
}

/**
 * 初始化表格列表
 */
fun RecyclerView.initGridList(context: Context, spanCount: Int, adapter: RecyclerView.Adapter<*>) {
    layoutManager = GridLayoutManager(context, spanCount)
    hasFixedSize()
    this.adapter = adapter
}

/**
 * 產生列表項目
 */
fun ViewGroup.inflater(resId: Int): View {
    return LayoutInflater.from(context).inflate(resId, this, false)
}