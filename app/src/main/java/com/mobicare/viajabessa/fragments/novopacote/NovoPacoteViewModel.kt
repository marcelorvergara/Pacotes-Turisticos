package com.mobicare.viajabessa.fragments.novopacote

import android.net.Uri
import android.util.Log
import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.mobicare.viajabessa.R
import java.net.URI
import java.util.*

class NovoPacoteViewModel : ViewModel() {

    //voltar

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

    //id do doc inserido no firebase
    private val _docId = MutableLiveData<String>()
    val docId : LiveData<String>
        get() = _docId


    fun inserirDados(tit: String, desc: String, din: String){
        val uuid = UUID.randomUUID().toString()
        val db = Firebase.firestore
        val pacote = hashMapOf(
            "titulo" to tit,
            "descricao" to desc,
            "valor" to din,
            "uuid" to uuid
        )
        db.collection("pacotes").document(uuid)
            .set(pacote)
            .addOnSuccessListener {
                Log.i("TESTE", uuid)
                _docId.value = uuid
                _resultadoInsert.value = true
            }
            .addOnFailureListener { e ->
                Log.i("teste", e.toString())
            }
    }

}