//package com.sarnavsky.pasz.nighlight2.adapters
//
//import android.annotation.SuppressLint
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.sarnavsky.pasz.nighlight2.Interfaces.PlayMyMusic
//import com.sarnavsky.pasz.nighlight2.objects.MyAudio
//import com.sarnavsky.pasz.nighlight2.R
//
//class ListMusicAdapter : RecyclerView.Adapter<ListMusicAdapter.ListMusicHolder>() {
//
//    private val arrayList : ArrayList<MyAudio>? = null
//
//     private var currentMusicPosition = -1
//
//    var pressedPosition = -1
//
//    val playMyMusic : PlayMyMusic? = null
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListMusicHolder {
//        val view: View = LayoutInflater.from(parent.context)
//            .inflate(R.layout.item_list, parent, false)
//        return ListMusicHolder(view)
//    }
//
//    @SuppressLint("NotifyDataSetChanged")
//    override fun onBindViewHolder(holder: ListMusicHolder, position: Int) {
//       // holder.music_name.setText(arrayList?.get(position)?.getName())
//        //holder.music_author.setText(arrayList?.get(position)?.getAuth())
//        holder.itemView.setOnClickListener {
//            currentMusicPosition = -1
//            if (pressedPosition == position) {
//                playMyMusic?.pressPosition(position, false)
//                pressedPosition = -1
//            } else {
//                playMyMusic?.pressPosition(position, true)
//                pressedPosition = position
//            }
//            notifyDataSetChanged()
//        }
//        if (position == pressedPosition) {
//            holder.play_item.setImageResource(R.drawable.pausa)
//        } else {
//            holder.play_item.setImageResource(R.drawable.play)
//        }
//        if (position == currentMusicPosition && currentMusicPosition != -1) {
//            holder.play_item.setImageResource(R.drawable.pausa)
//        }
//    }
//
//    override fun getItemCount(): Int {
//        return arrayList!!.size
//    }
//
//
//
//
//    class ListMusicHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val play_item: ImageView
//        val music_name: TextView
//        val music_author: TextView
//
//        init {
//            play_item = itemView.findViewById(R.id.image)
//            music_name = itemView.findViewById(R.id.textMenu)
//            music_author = itemView.findViewById(R.id.author)
//        }
//    }
//
//
//}