package com.mobicare.viajabessa.fragments.home

import java.io.Serializable

data class Pacote(
    var titulo: String,
    var descricao: String,
    var valor: String,
    var imageUrl: String
): Serializable{
    constructor() : this("","","","")

    fun getPacotes():String{
        val infos = "$titulo, $descricao, $valor, $imageUrl"
        return infos
    }
}

