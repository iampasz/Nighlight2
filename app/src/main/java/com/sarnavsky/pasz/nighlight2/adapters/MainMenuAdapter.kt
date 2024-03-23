package com.sarnavsky.pasz.nighlight2.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sarnavsky.pasz.nighlight2.databinding.ItemMenuBinding
import com.sarnavsky.pasz.nighlight2.objects.MenuItem


class MainMenuAdapter(
    private val itemSelected: (MenuItem) -> Unit
) : RecyclerView.Adapter<MainMenuAdapter.TypeOfActivityVH>() {

    class TypeOfActivityVH(binding: ItemMenuBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val callback = object : DiffUtil.ItemCallback<MenuItem>() {
        override fun areItemsTheSame(
            oldItem: MenuItem,
            newItem: MenuItem
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: MenuItem,
            newItem: MenuItem
        ): Boolean {
            return false
        }

    }

    val list = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeOfActivityVH {
        return TypeOfActivityVH(
            ItemMenuBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = list.currentList.size

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: TypeOfActivityVH, position: Int) {
        val item = list.currentList[position]
        ItemMenuBinding.bind(holder.itemView).apply {

            textMenu.text = item.name
            colorShape.setColorFilter(item.color)
            iconImage.setImageResource(item.iconImage)



        }
    }

}