package com.mobicare.viajabessa.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mobicare.viajabessa.R
import com.mobicare.viajabessa.databinding.FragmentLoginBinding


/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private var user: FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //inicializando auth do firebase
        auth = Firebase.auth

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<FragmentLoginBinding>(inflater, R.layout.fragment_login,container,false)

        //voltar para a tela inicial
        binding.btnVoltar.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        }

        //realizar o login no firebase auth
        binding.btnLogin.setOnClickListener { view : View ->
            auth.signInWithEmailAndPassword(binding.edtLogin.toString(),binding.edtPass.toString())
                .addOnCompleteListener(requireActivity()){ task ->
                    if (task.isSuccessful){
                        //cachear o user atual
                        user = auth.currentUser
                        //ir para tela de inclusão de pacotes
                        view.findNavController().navigate(R.id.action_loginFragment_to_novoPacoteFragment)
                    } else {
                        Toast.makeText(requireContext(), "Falha na autenticação. Verifique o login e a senha.", Toast.LENGTH_SHORT).show()
                    }
                }
        }


        return binding.root
    }

    override fun onStart() {
        super.onStart()
        //verificar se o usuário está conectado (logado)
        val currentUser = auth.currentUser
        if(currentUser != null){
            findNavController().navigate(R.id.action_loginFragment_to_novoPacoteFragment)
        }
    }
}