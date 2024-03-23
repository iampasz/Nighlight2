//package com.sarnavsky.pasz.nighlight2.adapters
//
//import android.graphics.Color
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import androidx.recyclerview.widget.RecyclerView
//import com.daimajia.androidanimations.library.Techniques
//import com.daimajia.androidanimations.library.YoYo
//import com.sarnavsky.pasz.nighlight2.objects.Nightlighter
//import com.sarnavsky.pasz.nighlight2.R
//import java.util.Random
//
//class Nightlighters : RecyclerView.Adapter<Nightlighters.NightlightHolder>() {
//
//    var arrayList: java.util.ArrayList<Nightlighter>? = null
//    lateinit var bgNlColors: Array<String>
//    var random = Random()
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NightlightHolder {
//        val v: View =
//            LayoutInflater.from(parent.context).inflate(R.layout.pager_fragment, parent, false)
//        return NightlightHolder(v)
//    }
//
//    override fun getItemCount(): Int {
//        return 10
//        //return arrayList.size
//    }
//
//
//    override fun onBindViewHolder(holder: NightlightHolder, position: Int) {
//        //arrayList?.get(position)?.let { holder.upImg.setImageResource(it.getUpImg()) }
//        //arrayList?.get(position)?.let { holder.underImg.setImageResource(it.getDownImg()) }
//        val color: Int = random.nextInt(bgNlColors.size)
//        holder.underImg.setColorFilter(Color.parseColor(bgNlColors.get(color)))
//        val techniques = ArrayList<Techniques>()
//        techniques.add(Techniques.Bounce)
//        techniques.add(Techniques.BounceIn)
//        techniques.add(Techniques.FadeIn)
//        techniques.add(Techniques.DropOut)
//        techniques.add(Techniques.Shake)
//        techniques.add(Techniques.Flash)
//        techniques.add(Techniques.SlideInLeft)
//        techniques.add(Techniques.Swing)
//        techniques.add(Techniques.FlipInY)
//        holder.itemView.setOnClickListener {
//            val random = Random()
//            val i = random.nextInt(techniques.size)
//            YoYo.with(techniques[i])
//                .duration(700)
//                .playOn(holder.itemView)
//        }
//    }
//
//    class NightlightHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        var upImg: ImageView
//        var underImg: ImageView
//
//        init {
//            upImg = itemView.findViewById<View>(R.id.upImg) as ImageView
//            underImg = itemView.findViewById<View>(R.id.underImg) as ImageView
//        }
//    }
//
//}