package com.mobicare.viajabessa.fragments.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.mobicare.viajabessa.R
import com.mobicare.viajabessa.databinding.FragmentHomeBinding


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding =  DataBindingUtil.inflate<FragmentHomeBinding>(inflater, R.layout.fragment_home,container,false)

        //inicializando o view model
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)


        binding.btnAcessoRtrt.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_homeFragment_to_loginFragment) }

        //RV adapter
        val adapter = HomeAdapter()
        binding.pacotesList.adapter = adapter

        //chamando a query do firestore
        viewModel.getPacotes()

        viewModel.pkts.observe(viewLifecycleOwner, Observer {
            it.let {
                adapter.submitList(it)
            }
        })

        return binding.root
    }


}