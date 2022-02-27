package com.example.cupcake

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.cupcake.databinding.FragmentNameBinding
import com.example.cupcake.model.OrderViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [NameFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NameFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private var binding: FragmentNameBinding? = null
    private val sharedViewModel: OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNameBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            viewModel = sharedViewModel
            lifecycleOwner = viewLifecycleOwner
            nameFragment = this@NameFragment
        }
    }

    fun flavorScreen(){
        val nameValue = binding?.nameInput?.text?.toString() ?: " "
        sharedViewModel.setName(nameValue)
        findNavController().navigate(NameFragmentDirections.actionNameFragmentToFlavorFragment())
    }

    fun cancelOrder(){
        sharedViewModel.resetOrder()
        findNavController().navigate(NameFragmentDirections.actionNameFragmentToStartFragment())
    }

}