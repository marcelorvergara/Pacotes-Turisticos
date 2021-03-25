package com.mobicare.viajabessa.fragments.novopacote

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mobicare.viajabessa.R

class NovoPacoteViewModel : ViewModel() {



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

    //inserção de dados no firestore
    private val _resultadoInsert = MutableLiveData<Boolean>()
    val resultadoInsert : LiveData<Boolean>
        get() = _resultadoInsert

    init {
        _resultadoInsert.value = false
    }

    fun inserirDados(tit: String, desc: String, din: String, img: String){
        Log.i("teste", tit)
        val db = Firebase.firestore
        val pacote = hashMapOf(
            "titulo" to tit,
            "descricao" to desc,
            "valor" to din,
            "imagem" to img
        )
        db.collection("pacotes")
            .add(pacote)
            .addOnSuccessListener { documentRef ->
                Log.i("teste", documentRef.id)
                _resultadoInsert.value = true

            }
            .addOnFailureListener { e ->
                Log.i("teste", e.toString())
            }
    }



}