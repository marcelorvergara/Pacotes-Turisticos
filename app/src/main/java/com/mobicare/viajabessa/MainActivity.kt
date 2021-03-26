package com.mobicare.viajabessa

import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //inicializando o firebase
        FirebaseApp.initializeApp(this);

        //pegar as informações
        //        Toast.makeText(this,"Versão do Android: ${android.os.Build.VERSION.RELEASE}",Toast.LENGTH_SHORT).show()
        //        Toast.makeText(this,"Marca do Aparelho: ${android.os.Build.MANUFACTURER}",Toast.LENGTH_SHORT).show()
        //        Toast.makeText(this,"Modelo do Aparelho: ${android.os.Build.MODEL}",Toast.LENGTH_SHORT).show()

        val sharedPreferences: SharedPreferences = this.getSharedPreferences( "arqInfos", MODE_PRIVATE)

        val sharedValue = sharedPreferences.getString("uuid","none")
        //verificar se as informações já foram enviadas
        if (sharedValue == "none"){
            Toast.makeText(this,"Nenhuma Info. Vamos coletar!", Toast.LENGTH_SHORT).show()
            val uuid = UUID.randomUUID().toString()
            //armazenando localmente
            val editor:SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString("Android",android.os.Build.VERSION.RELEASE)
            editor.putString("Marca",android.os.Build.MANUFACTURER)
            editor.putString("Modelo",android.os.Build.MODEL)
            editor.putString("uuid", uuid)
            editor.apply()
            editor.commit()

            //enviando as infos para a nuvem
            val db = Firebase.firestore
            val infos = hashMapOf(
                "Android" to android.os.Build.VERSION.RELEASE,
                "Marca" to android.os.Build.MANUFACTURER,
                "Modelo" to android.os.Build.MODEL,
                "uuid" to uuid
            )

            db.collection("infos").document(uuid)
                .set(infos)
                .addOnSuccessListener {
                    Toast.makeText(this,"informações armazenadas com o id ${uuid}",Toast.LENGTH_SHORT).show()
                }

        }else{
            Toast.makeText(this,"Informações já coletadas",Toast.LENGTH_SHORT).show()
        }





    }
}