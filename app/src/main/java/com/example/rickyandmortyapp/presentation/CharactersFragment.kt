package com.example.rickyandmortyapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.rickyandmortyapp.R
import com.example.rickyandmortyapp.databinding.FragmentCharactersBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharactersFragment : Fragment() {

  private var _binding: FragmentCharactersBinding? = null
  private val binding get() = _binding!!

  private val rickViewModel: RickyViewModel by viewModel()
  private val adapter = MortyAdapter()

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
    getAndObserveCharacters()
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }

  private fun getUserName() {
    rickViewModel.getUserName(requireContext())
  }

  private fun observeName() {
    rickViewModel.name.observe(viewLifecycleOwner) { name ->
      val welcomeText = getString(R.string.message, name)
      binding.messageTextView.text = welcomeText
    }
  }

  private fun getAndObserveCharacters() {
    binding.mortyRecyclerView.adapter = adapter

    rickViewModel.getCharacters()

    lifecycleScope.launch {
      rickViewModel.characters.collectLatest { state ->
        binding.progressbar.isVisible = state.isLoading
        when {
          state.data.isNotEmpty() -> {
            adapter.submitList(state.data)
          }

          state.error -> {
            Toast.makeText(requireActivity(),"Error Occurred",Toast.LENGTH_LONG).show()
          }
        }
      }
    }
  }
}