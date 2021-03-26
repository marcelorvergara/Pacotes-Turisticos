package com.mobicare.viajabessa.fragments.novopacote

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mobicare.viajabessa.R
import com.mobicare.viajabessa.databinding.FragmentNovoPacoteBinding


/**
 * A simple [Fragment] subclass.
 * Use the [NovoPacoteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NovoPacoteFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var viewModel: NovoPacoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //inicializando auth do firebase
        auth = Firebase.auth

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentNovoPacoteBinding>(inflater,R.layout.fragment_novo_pacote,container,false)

        //inicializando o view model
        viewModel = ViewModelProvider(this).get(NovoPacoteViewModel::class.java)

        //voltar
        binding.btnVolta.setOnClickListener {
            findNavController().navigate(R.id.action_novoPacoteFragment_to_homeFragment)
        }

        //logout do app e volta para tela inicial
        viewModel.logout.observe(viewLifecycleOwner, Observer { logout ->
            if(logout){
                findNavController().navigate(R.id.action_novoPacoteFragment_to_homeFragment)
                viewModel.onLogoutCompleto()
            }
        })

        binding.btnLogout.setOnClickListener { viewModel.logoutFunc() }

        //inserir dados / binding para event handling
        binding.btnInserir.setOnClickListener {
            //textos
            viewModel.inserirDados(
            binding.edtTitulo.text.toString(),
            binding.edtDescricao.text.toString(),
            binding.edtValor.text.toString())
        }

        viewModel.resultadoInsert.observe(viewLifecycleOwner, Observer { result ->
            if(result){
                findNavController().navigate(NovoPacoteFragmentDirections.actionNovoPacoteFragmentToImagemPacoteFragment(viewModel.docId.value.toString()))
                Toast.makeText(requireContext(),"Informações inseridas. Escolha uma imagem para o pacote.",Toast.LENGTH_SHORT).show()
            }
        })

        return binding.root
    }

}