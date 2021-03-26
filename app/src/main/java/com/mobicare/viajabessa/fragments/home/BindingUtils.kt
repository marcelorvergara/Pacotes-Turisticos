package com.mobicare.viajabessa.fragments.home

import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("titulo")
fun TextView.setTituloFormatado(item:Pacote){
    text = (item.titulo)
}

@BindingAdapter("valor")
fun TextView.setValorFormatado(item:Pacote){
    text = ("R$ ${item.valor},00")
}

@BindingAdapter("imageUrl")
fun ImageView.setImagemFormatada(item:Pacote) {
    if (item.imageUrl.startsWith("http", false)){
        Log.d("Imagem", item.imageUrl)
        Picasso.get().load(item.imageUrl).into(this)
    }

}
