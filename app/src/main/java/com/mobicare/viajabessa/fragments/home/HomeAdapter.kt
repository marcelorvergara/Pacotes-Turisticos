package com.mobicare.viajabessa.fragments.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mobicare.viajabessa.databinding.PktItemViewBinding

class HomeAdapter(val clicklistener: HomeListener): ListAdapter<Pacote, HomeAdapter.ViewHolder>(HomeDiffCallback()){

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!,clicklistener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: PktItemViewBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item: Pacote, clickListener: HomeListener){
            binding.clickListener = clickListener
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

class HomeListener(val clickListener: (pktId: Pacote) -> Unit){
    fun onClick(pacote: Pacote) = clickListener(pacote)
}