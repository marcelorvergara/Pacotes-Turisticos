package com.mobicare.viajabessa.fragments.detalhepacote

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.mobicare.viajabessa.R
import com.mobicare.viajabessa.databinding.FragmentDetalhePacoteBinding
import com.mobicare.viajabessa.databinding.FragmentHomeBinding
import com.squareup.picasso.Picasso


/**
 * A simple [Fragment] subclass.
 * Use the [DetalhePacoteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetalhePacoteFragment : Fragment() {

    private lateinit var viewModel: DetalhePacoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding =  DataBindingUtil.inflate<FragmentDetalhePacoteBinding>(inflater, R.layout.fragment_detalhe_pacote,container,false)

        //inicializar o view model
        viewModel = ViewModelProvider(this).get(DetalhePacoteViewModel::class.java)

        binding.detalhePacoteViewModel = viewModel

        val args = DetalhePacoteFragmentArgs.fromBundle(requireArguments())

        viewModel.titulo = args.pacote.titulo
        viewModel.valor = args.pacote.valor
        viewModel.descricao = args.pacote.descricao

        Picasso.get().load(args.pacote.imageUrl).into(binding.imgPktDetalhe)

        //Toast.makeText(context,"Título do Pacote: ${args.pacote.titulo}",Toast.LENGTH_SHORT).show()

        return binding.root


    }


}