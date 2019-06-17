package com.example.myresume.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myresume.R
import com.example.myresume.model.ResumeSection

class ResumeAdapter(private var resumeSections: Array<ResumeSection>) :
    RecyclerView.Adapter<ResumeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.resume_row, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.header.text = resumeSections[position].header
        holder.desc.text = resumeSections[position].toString()
    }

    fun setData(resumeSections: Array<ResumeSection>) {
        this.resumeSections = resumeSections
    }

    override fun getItemCount(): Int {
        return resumeSections.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var header: TextView = itemView.findViewById(R.id.header)
        internal var desc: TextView = itemView.findViewById(R.id.desc)

    }
}
