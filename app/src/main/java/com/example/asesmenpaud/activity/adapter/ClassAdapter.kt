package com.example.asesmenpaud.activity.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.asesmenpaud.R
import com.example.asesmenpaud.activity.ClassDetailActivity
import com.example.asesmenpaud.data.ListClass
import com.example.asesmenpaud.databinding.ClassListBinding

class ClassAdapter : ListAdapter<ListClass, ClassAdapter.MyViewHolder>(DIFF_CALLBACK){
    class MyViewHolder(val binding: ClassListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(holder: MyViewHolder, `class`: ListClass){
            binding.className.text = holder.itemView.context.getString(R.string.nama_kelas, `class`.className)
            binding.classDesc.text = `class`.classDesc
            binding.layout.setOnClickListener {
                val i = Intent(holder.itemView.context, ClassDetailActivity::class.java)
                i.putExtra(ClassDetailActivity.CLASS_KEY, `class`)
                holder.itemView.context.startActivity(i)
//                val activity = holder.itemView.context as MainActivity
//                activity.showDetailKelasActivity(`class`)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ClassListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val story = getItem(position)
        holder.bind(holder, story)
    }
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ListClass>() {
            override fun areItemsTheSame(oldItem: ListClass, newItem: ListClass): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: ListClass, newItem: ListClass): Boolean {
                return oldItem == newItem
            }
        }
    }
}