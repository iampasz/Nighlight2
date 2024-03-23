//package com.sarnavsky.pasz.nighlight2.adapters
//
//import android.annotation.SuppressLint
//import android.graphics.Color
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.sarnavsky.pasz.nighlight2.Interfaces.ChangeColors
//import com.sarnavsky.pasz.nighlight2.Interfaces.OpenColorFragment
//import com.sarnavsky.pasz.nighlight2.objects.MenuItem
//import com.sarnavsky.pasz.nighlight2.R
//
//class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {
//
//
//    var menuButtons: ArrayList<MenuItem>? = null
//
//    var changeColors: ChangeColors? = null
//    lateinit var colors: Array<String>
//    var i = 0
//    var openColorFragment: OpenColorFragment? = null
//
////    fun MyOnclick(changeColors: ChangeColors?) {
////        this.changeColors = changeColors
////    }
////
////    fun MyOnLongclick(openColorFragment: OpenColorFragment?) {
////        this.openColorFragment = openColorFragment
////    }
////
////    fun RecyclerViewAdapterOld(menuButtons: ArrayList<*>?, colors: Array<String>) {
////        this.menuButtons = menuButtons as ArrayList<MenuItem>?
////        this.colors = colors
////    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//        val v: View =
//            LayoutInflater.from(parent.context).inflate(R.layout.item_menu, parent, false)
//        return MyViewHolder(v)
//    }
//
//
//
//    override fun onBindViewHolder(holder: MyViewHolder, @SuppressLint("RecyclerView") position: Int) {
//        holder.colorShape.setColorFilter(menuButtons!![position].color)
//       // holder.iconImage.setImageResource(menuButtons!![position].iconImege)
//        holder.itemView.setOnClickListener {
//            changeColors!!.onclick(menuButtons!![position].button)
//            holder.colorShape.setColorFilter(Color.parseColor(colors[i]))
//            i++
//            if (i >= colors.size) {
//                i = 0
//            }
//        }
//        holder.itemView.setOnLongClickListener {
//            openColorFragment!!.onclick(menuButtons!![position].button)
//            true
//        }
//       //holder.textMenu.setText(menuButtons!![position].text)
//    }
//
//    override fun getItemCount(): Int {
//        return menuButtons!!.size
//    }
//
//    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        var colorShape: ImageView
//        var iconImage: ImageView
//        var textMenu: TextView
//
//        init {
//            colorShape = itemView.findViewById<View>(R.id.colorShape) as ImageView
//            iconImage = itemView.findViewById<View>(R.id.iconImage) as ImageView
//            textMenu = itemView.findViewById<View>(R.id.textMenu) as TextView
//        }
//    }
//}