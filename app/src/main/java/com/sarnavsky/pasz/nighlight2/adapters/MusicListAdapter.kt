package com.sarnavsky.pasz.nighlight2.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sarnavsky.pasz.nighlight2.R
import com.sarnavsky.pasz.nighlight2.databinding.ItemAudioBinding
import com.sarnavsky.pasz.nighlight2.objects.AudioItem


class MusicListAdapter(
    private val itemSelected: (AudioItem) -> Unit
) : RecyclerView.Adapter<MusicListAdapter.TypeOfActivityVH>() {

    private var oldPosition = -1

    class TypeOfActivityVH(binding: ItemAudioBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val callback = object : DiffUtil.ItemCallback<AudioItem>() {
        override fun areItemsTheSame(
            oldItem: AudioItem,
            newItem: AudioItem
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: AudioItem,
            newItem: AudioItem
        ): Boolean {
            return false
        }

    }

    val list = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeOfActivityVH {
        return TypeOfActivityVH(
            ItemAudioBinding.inflate(
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

        ItemAudioBinding.bind(holder.itemView).apply {

            audioName.text = item.audioName
            audioAuthor.text = item.audioAuth
            holder.itemView.setOnClickListener {

                if(oldPosition!=-1){
                    val oldItem = list.currentList[oldPosition]
                    if (oldPosition != position) {
                        oldItem.status = false
                    }
                }

                item.status = !item.status

                notifyItemChanged(position, image)
                notifyItemChanged(oldPosition, image)


                itemSelected(item)
            }

            if (item.status) {
                image.setImageResource(R.drawable.pausa)
                oldPosition = holder.absoluteAdapterPosition
            } else {
                image.setImageResource(R.drawable.play)
            }

        }
    }

//    private fun ItemCheckMarkBinding.setSelected() {
//
//    }

}