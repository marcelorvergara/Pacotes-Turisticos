package com.mobicare.viajabessa.fragments.novopacote

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.mobicare.viajabessa.R
import com.mobicare.viajabessa.databinding.FragmentImagemPacoteBinding


/**
 * A simple [Fragment] subclass.
 * Use the [ImagemPacoteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ImagemPacoteFragment : Fragment() {

    private lateinit var viewModel : ImagemPacoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val args = ImagemPacoteFragmentArgs.fromBundle(requireArguments())

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentImagemPacoteBinding>(inflater,R.layout.fragment_imagem_pacote,container,false)

        viewModel = ViewModelProvider(this).get(ImagemPacoteViewModel::class.java)

        return binding.root
    }


}