package com.mobicare.viajabessa.fragments.novopacote

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
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

        //logout do app e volta para tela inicial
        viewModel.logout.observe(viewLifecycleOwner, Observer { logout ->
            if(logout){
                findNavController().navigate(R.id.action_novoPacoteFragment_to_homeFragment)
                viewModel.onLogoutCompleto()
            }
        })

        binding.btnLogout.setOnClickListener { viewModel.logoutFunc() }

        return binding.root
    }

}