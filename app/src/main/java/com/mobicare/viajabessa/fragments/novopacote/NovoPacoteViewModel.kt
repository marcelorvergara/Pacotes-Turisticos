package com.mobicare.viajabessa.fragments.novopacote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mobicare.viajabessa.R

class NovoPacoteViewModel : ViewModel() {

    //título do pacote
    var titulo = ""

    //descrição
    var descricao = ""

    //valor
    var valor = 0.0

    //imagem do pacote
    var imagem = ""

    //logout + navegação
    private val _logout = MutableLiveData<Boolean>()
    val logout: LiveData<Boolean>
        get() = _logout

    fun logoutFunc(){
        Firebase.auth.signOut()
        _logout.value = true

    }

    fun onLogoutCompleto(){
        _logout.value = false
    }

}