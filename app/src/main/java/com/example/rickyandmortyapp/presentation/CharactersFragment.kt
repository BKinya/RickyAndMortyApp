package com.example.rickyandmortyapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.rickyandmortyapp.R
import com.example.rickyandmortyapp.databinding.FragmentCharactersBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharactersFragment : Fragment() {

  private var _binding: FragmentCharactersBinding? = null
  private val binding get() = _binding!!

  private val rickViewModel: RickyViewModel by viewModel()

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {

    _binding = FragmentCharactersBinding.inflate(inflater, container, false)
    return binding.root

  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    getUserName()
    observeName()
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }

  private fun getUserName(){
    rickViewModel.getUserName(requireContext())
  }

  private fun observeName(){
    rickViewModel.name.observe(viewLifecycleOwner){ name ->
      val welcomeText = getString(R.string.message, name)
      binding.messageTextView.text =  welcomeText

    }
  }
}