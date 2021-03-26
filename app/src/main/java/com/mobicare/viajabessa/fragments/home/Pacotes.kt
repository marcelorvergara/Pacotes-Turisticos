package com.mobicare.viajabessa.fragments.home

import java.io.Serializable

data class Pacote(
    var titulo: String,
    var descricao: String,
    var valor: String,
    var imagem: String
): Serializable{
    constructor() : this("","","","")

    fun getPacotes():String{
        val infos = "$titulo, $descricao, $valor, $imagem"
        return infos
    }
}

