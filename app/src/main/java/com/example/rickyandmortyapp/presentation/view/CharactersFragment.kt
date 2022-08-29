package com.example.rickyandmortyapp.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.rickyandmortyapp.R
import com.example.rickyandmortyapp.databinding.FragmentCharactersBinding
import com.example.rickyandmortyapp.presentation.intent.CharacterIntent
import com.example.rickyandmortyapp.presentation.model.UiState
import com.google.android.material.snackbar.Snackbar
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
        registerIntent()
        observeViewState()
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

    private fun registerIntent() {
        lifecycleScope.launch {
            rickViewModel.characterIntent.send(CharacterIntent.FetchCharacters)
        }
    }

    private fun observeViewState() {
        binding.mortyRecyclerView.adapter = adapter

        lifecycleScope.launch {
            rickViewModel.viewState.collectLatest { state ->
                when (state) {
                    is UiState.LoadingState -> {
                        binding.progressbar.isVisible = true
                    }

                    is UiState.Characters -> {
                        binding.progressbar.isVisible = false
                        val data = state.data
                        adapter.submitList(data)
                    }

                    is UiState.Error -> {
                        val errorMsg = state.error
                        binding.progressbar.isVisible = false
                        Snackbar.make(binding.root, errorMsg, Snackbar.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}
