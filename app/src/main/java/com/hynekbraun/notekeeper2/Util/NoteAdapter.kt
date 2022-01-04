package com.hynekbraun.notekeeper2.Util

import android.content.res.ColorStateList
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.hynekbraun.notekeeper2.R
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class NoteAdapter(
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<NoteAdapter.MyViewHolder>() {
    private var notesList: ArrayList<NoteEntity> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MyViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    inner class MyViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.rv_item, parent, false)),
        View.OnClickListener {
        private var background: ConstraintLayout? = null
        private var headerView: TextView? = null
        private var dateView: TextView? = null
        private var isDone: ImageView? = null

        init {
            background = itemView.findViewById(R.id.cl_rv_item_background)
            headerView = itemView.findViewById(R.id.tv_rvItem_header)
            dateView = itemView.findViewById(R.id.tv_rvItem_date)
            isDone = itemView.findViewById(R.id.iv_rvItem_isDone)
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }

        fun bind(item: NoteEntity) {
            headerView?.text = item.header
            dateView?.text = getFormatedDate(item.date)
            if (item.isDone == true) {
                isDone?.visibility = View.VISIBLE
            } else {
                isDone?.visibility = View.INVISIBLE
            }
            background?.backgroundTintList = ColorStateList.valueOf(item.color)
        }

        private fun getFormatedDate(date: Date): String {
            val formatter = SimpleDateFormat("MM-dd HH:mm")
            return formatter.format(date)
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val note = notesList[position]
        holder.bind(note)
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun getNotes(notes: ArrayList<NoteEntity>) {
        notesList = notes
        notifyDataSetChanged()
    }
}
