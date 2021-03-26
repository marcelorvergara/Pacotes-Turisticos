package com.mobicare.viajabessa.fragments.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mobicare.viajabessa.databinding.PktItemViewBinding
import com.squareup.picasso.Picasso

class HomeAdapter: ListAdapter<Pacote, HomeAdapter.ViewHolder>(HomeDiffCallback()){

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: PktItemViewBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item:Pacote){
//            val res = itemView.context.resources
//            binding.txtTituloPkt.text = item.titulo
//            binding.txtValor.text = item.valor
//            Log.d("Imagem", item.imageUrl)
//            if (item.imageUrl.startsWith("http", true)) {
//                Picasso.get().load(item.imageUrl).into(binding.imgPkt)
//            }
            binding.pacote = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = PktItemViewBinding.inflate(layoutInflater,parent,false)
                return ViewHolder(binding)
            }
        }

    }

}

class HomeDiffCallback: DiffUtil.ItemCallback<Pacote>(){
    override fun areItemsTheSame(oldItem: Pacote, newItem: Pacote): Boolean {
        return oldItem.uuid == newItem.uuid
    }

    override fun areContentsTheSame(oldItem: Pacote, newItem: Pacote): Boolean {
        return oldItem == newItem
    }
}

class HomeListener(val clickListener: (pacoteId: String) -> Unit){
    fun onClick(pacote: Pacote) = clickListener(pacote.uuid)
}