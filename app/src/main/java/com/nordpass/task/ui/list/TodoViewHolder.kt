package com.nordpass.task.ui.list

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nordpass.task.R
import com.nordpass.tt.usecase.Todo

class TodoViewHolder private constructor(
    view: View,
    private val listener: (Todo) -> Unit
) : RecyclerView.ViewHolder(view) {

    private val itemTitleTextView: TextView =
        itemView.findViewById<TextView>(R.id.itemTitleTextView)

    private var item: Todo? = null

    init {
        itemView.setOnClickListener { item?.let(listener) }
    }

    fun bind(todo: Todo) {
        this.item = todo

        itemTitleTextView.text = todo.title
        itemTitleTextView.paintFlags = if (todo.isCompleted) {
            itemTitleTextView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            itemTitleTextView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    companion object {
        fun create(parent: ViewGroup, listener: (Todo) -> Unit): TodoViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val view = inflater.inflate(R.layout.item_todo, parent, false)
            return TodoViewHolder(view, listener)
        }
    }
}