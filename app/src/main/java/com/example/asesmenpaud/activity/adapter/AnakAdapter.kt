package com.example.asesmenpaud.activity.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.asesmenpaud.R
import com.example.asesmenpaud.activity.AnakDetailActivity
import com.example.asesmenpaud.activity.ClassDetailActivity
import com.example.asesmenpaud.data.ListAnakItem
import com.example.asesmenpaud.databinding.AnakListBinding

class AnakAdapter : ListAdapter<ListAnakItem, AnakAdapter.MyViewHolder>(DIFF_CALLBACK){
    class MyViewHolder(val binding: AnakListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(holder: MyViewHolder, anak: ListAnakItem){
            binding.anakName.text = anak.name
            binding.layout.setOnClickListener {
                val i = Intent(holder.itemView.context, AnakDetailActivity::class.java)
                i.putExtra(AnakDetailActivity.ANAK_KEY, anak)
                holder.itemView.context.startActivity(i)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =AnakListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val anak = getItem(position)
        holder.bind(holder, anak)
    }
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ListAnakItem>() {
            override fun areItemsTheSame(oldItem: ListAnakItem, newItem: ListAnakItem): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: ListAnakItem, newItem: ListAnakItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}