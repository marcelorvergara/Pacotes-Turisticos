package com.mobicare.viajabessa.fragments.home

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mobicare.viajabessa.R
import com.squareup.picasso.Picasso

class HomeAdapter: RecyclerView.Adapter<HomeAdapter.ViewHolder> (){
    var data = listOf<Pacote>()
        set(value){
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(item:Pacote){
            val res = itemView.context.resources
            titulo.text = item.titulo
            valor.text = item.valor
            Log.d("Imagem", item.imageUrl)
            if (item.imageUrl.startsWith("http", true)) {
                Picasso.get().load(item.imageUrl).into(imagemPkt)
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.pkt_item_view, parent, false)
                return ViewHolder(view)
            }
        }

        val titulo: TextView = itemView.findViewById(R.id.txtTituloPkt)
        val valor: TextView = itemView.findViewById(R.id.txtValor)
        val imagemPkt: ImageView = itemView.findViewById(R.id.imgPkt)

    }

}