package com.sarnavsky.pasz.nighlight2.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sarnavsky.pasz.nighlight2.databinding.NightlightFragmentBinding
import com.sarnavsky.pasz.nighlight2.objects.Nightlighter


class NightlightersAdapter(
    private val itemSelected: (Nightlighter) -> Unit
) : RecyclerView.Adapter<NightlightersAdapter.TypeOfActivityVH>() {

    class TypeOfActivityVH(binding: NightlightFragmentBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val callback = object : DiffUtil.ItemCallback<Nightlighter>() {
        override fun areItemsTheSame(
            oldItem: Nightlighter,
            newItem: Nightlighter
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: Nightlighter,
            newItem: Nightlighter
        ): Boolean {
            return false
        }

    }

    val list = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeOfActivityVH {
        return TypeOfActivityVH(
            NightlightFragmentBinding.inflate(
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
        NightlightFragmentBinding.bind(holder.itemView).apply {

            underImg.setImageResource(item.downImg)
            upImg.setImageResource(item.upImg)

        }
    }

}