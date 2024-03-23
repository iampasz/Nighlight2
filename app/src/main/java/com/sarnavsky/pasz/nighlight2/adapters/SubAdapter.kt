//package com.sarnavsky.pasz.nighlight2.adapters
//
//import android.graphics.Paint
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.LinearLayout
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.android.billingclient.api.ProductDetails.SubscriptionOfferDetails
//import com.sarnavsky.pasz.nighlight2.Interfaces.ChoseSub
//import com.sarnavsky.pasz.nighlight2.R
//
//class SubAdapter : RecyclerView.Adapter<SubAdapter.ListSubHolder>() {
//
//
//    var list: List<SubscriptionOfferDetails>? = null
//    var pressedPosition = 1
//    var currentMusicPosition = -1
//    var choseSub: ChoseSub? = null
//
//    fun SubAdapterOld(list: List<SubscriptionOfferDetails>?, choseSub: ChoseSub?) {
//        this.list = list
//        this.choseSub = choseSub
//    };
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListSubHolder {
//        val view: View = LayoutInflater.from(parent.context)
//            .inflate(R.layout.sub_item, parent, false)
//        return ListSubHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: ListSubHolder, position: Int) {
//        holder.crossed_text.paintFlags =
//            holder.crossed_text.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
//        holder.sub_month.setBackgroundResource(R.drawable.sub_button_large)
//        holder.s_m_text.alpha = 0.5f
//        holder.sub_month.alpha = 0.5f
//        when (list!![position].basePlanId) {
//            "month3" -> holder.s_m_text.setText(R.string.month3)
//            "month1" -> holder.s_m_text.setText(R.string.month1)
//            "month-12" -> holder.s_m_text.setText(R.string.month12)
//        }
//        var microPrice =
//            list!![position].pricingPhases.pricingPhaseList[0].priceAmountMicros.toInt()
//        microPrice = microPrice / 1000000
//        val priceDiscount = microPrice * 70 / 30 + microPrice
//        val currenc = list!![position].pricingPhases.pricingPhaseList[0].priceCurrencyCode
//
//
////                crossed_text1.setText();
////
////                price1.setText(microPrice+" "+currenc);
//        holder.crossed_text.text = "$priceDiscount $currenc"
//        holder.price.text = "$microPrice $currenc"
//        holder.sub_month.setOnClickListener { //currentMusicPosition=-1;
//            if (pressedPosition == holder.adapterPosition) {
//                //holder.play_item.setImageResource(R.drawable.bt_play);
//                Log.i(
//                    "Play",
//                    pressedPosition.toString() + " тут должен плей поставить " + holder.adapterPosition
//                )
//
//                //pressedPosition=-1;
//            } else {
//
//                //holder.play_item.setImageResource(R.drawable.bt_pause);
//                Log.i(
//                    "Play",
//                    pressedPosition.toString() + " тут должен стоп поставить " + holder.adapterPosition
//                )
//                pressedPosition = holder.adapterPosition
//            }
//            notifyDataSetChanged()
//        }
//        if (holder.adapterPosition == pressedPosition) {
//            holder.sub_month.setBackgroundResource(R.drawable.sub_button_large)
//            holder.crossed_text.setTextColor(holder.sub_month.resources.getColor(R.color.sub_text))
//            holder.s_m_text.alpha = 1f
//            holder.price.setTextColor(holder.sub_month.resources.getColor(R.color.sub_text))
//            holder.sub_month.alpha = 1f
//            holder.s_m_text.text = holder.s_m_text.text.toString() + " ✔ "
//            choseSub!!.setToken(list!![position].offerToken)
//        } else {
//        }
//    }
//
//    override fun getItemCount(): Int {
//        return list!!.size
//    }
//
//    class ListSubHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val s_m_text: TextView
//        val crossed_text: TextView
//        val price: TextView
//        val sub_month: LinearLayout
//
//        init {
//            s_m_text = itemView.findViewById<TextView>(R.id.s_m_text)
//            crossed_text = itemView.findViewById<TextView>(R.id.crossed_text)
//            price = itemView.findViewById<TextView>(R.id.price)
//            sub_month = itemView.findViewById<LinearLayout>(R.id.sub_month)
//        }
//    }
//}