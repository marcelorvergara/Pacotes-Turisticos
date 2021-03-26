package com.mobicare.viajabessa.fragments.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class HomeViewModel : ViewModel() {

    private val _pkts : MutableLiveData<List<Pacote>> = MutableLiveData()
    val pkts: LiveData<List<Pacote>>
        get() = _pkts

    //ler dados do firestore
    fun getPacotes(){
        val pacotesList : MutableList<Pacote> = mutableListOf()
        val db = Firebase.firestore.collection("pacotes").get()
        db.addOnSuccessListener { result ->
            for (doc in result){
                val pacote = doc.toObject<Pacote>()
                pacotesList.add(pacote)
                //Log.d("TESTE", "Doc ${doc.id} ==> ${doc.data}")
            }
            _pkts.value = pacotesList
        }
    }
}