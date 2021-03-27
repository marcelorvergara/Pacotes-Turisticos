package com.mobicare.viajabessa.fragments.novopacote

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.mobicare.viajabessa.R
import com.mobicare.viajabessa.databinding.FragmentImagemPacoteBinding
import java.io.IOException
import java.util.*


/**
 * A simple [Fragment] subclass.
 * Use the [ImagemPacoteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ImagemPacoteFragment : Fragment() {

    private lateinit var viewModel : ImagemPacoteViewModel

    //upload da imagem
    private val PICK_IMAGE_REQUEST = 71
    private var filePath: Uri? = null
    private var firebaseStore: FirebaseStorage? = null
    private var storageReference: StorageReference? = null

    lateinit var imageView: ImageView

    lateinit var args : ImagemPacoteFragmentArgs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //recebendo o docId para atualizar a imagem
        args = ImagemPacoteFragmentArgs.fromBundle(requireArguments())

        //inicializando o storage do firebase
        firebaseStore = FirebaseStorage.getInstance()
        storageReference = FirebaseStorage.getInstance().reference

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentImagemPacoteBinding>(inflater,R.layout.fragment_imagem_pacote,container,false)

        viewModel = ViewModelProvider(this).get(ImagemPacoteViewModel::class.java)

        //upload da imagem
        binding.imagePreview.setOnClickListener { abrirGaleria() }
        binding.btnConfirmar.setOnClickListener { enviarImagem() }

        //inicializando o imageView
        imageView = binding.imagePreview

        //voltar
        binding.btnVoltarImg.setOnClickListener {
            findNavController().navigate(R.id.action_imagemPacoteFragment_to_novoPacoteFragment)
        }

        return binding.root
    }

    private fun uploadImagem() {
        TODO("Not yet implemented")
    }

    private fun abrirGaleria() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            if(data == null || data.data == null){
                return
            }

            filePath = data.data
            imageView.setImageURI(filePath)
        }
    }


    //retirado de
    //https://adorahack.com/upload-gallery-image-to-firebase-from-android-app-kotlin
    private fun enviarImagem(){
        if(filePath != null){
            val ref = storageReference?.child("uploads/" + UUID.randomUUID().toString())
            val uploadTask = ref?.putFile(filePath!!)

            val urlTask = uploadTask?.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>> { task ->
                if (!task.isSuccessful) {
                    task.exception?.let {
                        throw it
                    }
                }
                return@Continuation ref.downloadUrl
            })?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val downloadUri = task.result
                    atualizarFirestore(downloadUri.toString())
                } else {
                    // Handle failures
                }
            }?.addOnFailureListener{

            }
        }else{
            Toast.makeText(requireContext(), "Escolha uma imagem para o pacote", Toast.LENGTH_SHORT).show()
        }
    }

    private fun atualizarFirestore(uri: String){
        val db = FirebaseFirestore.getInstance()

        val data = HashMap<String, Any>()
        data["imageUrl"] = uri

        db.collection("pacotes").document(args.idFirebase)
            .set(data, SetOptions.merge())
            .addOnSuccessListener { documentReference ->
                Toast.makeText(requireContext(), "Salvo no Banco de Dados", Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_imagemPacoteFragment_to_novoPacoteFragment)
            }
            .addOnFailureListener { e ->
                Toast.makeText(requireContext(), "Erro ao salvar no Banco de Dados", Toast.LENGTH_LONG).show()
            }
    }

}