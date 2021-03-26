package com.mobicare.viajabessa.fragments.detalhepacote

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.mobicare.viajabessa.R


/**
 * A simple [Fragment] subclass.
 * Use the [DetalhePacoteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetalhePacoteFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val args = DetalhePacoteFragmentArgs.fromBundle(requireArguments())
        Toast.makeText(context,"Título do Pacote: ${args.pacote.titulo}",Toast.LENGTH_SHORT).show()
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detalhe_pacote, container, false)
    }


}