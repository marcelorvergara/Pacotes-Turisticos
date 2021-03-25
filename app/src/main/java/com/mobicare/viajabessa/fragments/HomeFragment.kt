package com.mobicare.viajabessa.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.mobicare.viajabessa.R
import com.mobicare.viajabessa.databinding.FragmentHomeBinding


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding =  DataBindingUtil.inflate<FragmentHomeBinding>(inflater, R.layout.fragment_home,container,false)

        binding.btnAcessoRtrt.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_homeFragment_to_loginFragment) }

        return binding.root
    }


}