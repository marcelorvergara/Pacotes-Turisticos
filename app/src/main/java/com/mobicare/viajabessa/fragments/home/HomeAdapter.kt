package com.mobicare.viajabessa.fragments.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mobicare.viajabessa.R
import com.mobicare.viajabessa.TextItemViewHolder

class HomeAdapter: RecyclerView.Adapter<TextItemViewHolder> (){
    var data = listOf<Pacote>()
        set(value){
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        val item = data[position]
        holder.textView.text = item.titulo
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.pkt_item_view,parent,false) as TextView
        return TextItemViewHolder(view)
    }
}