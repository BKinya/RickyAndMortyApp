package com.example.rickyandmortyapp.presentation.view

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickyandmortyapp.domain.repository.RickyRepository
import com.example.rickyandmortyapp.presentation.intent.CharacterIntent
import com.example.rickyandmortyapp.presentation.model.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class RickyViewModel(
  private val repository: RickyRepository
) : ViewModel() {

  val characterIntent = Channel<CharacterIntent>(Channel.UNLIMITED)

  private val _name = MutableLiveData<String?>()
  val name: LiveData<String?>
    get() = _name

  private val _viewState = MutableStateFlow<UiState>(UiState.LoadingState)
  val viewState: StateFlow<UiState>
    get() = _viewState

  init {
      handleIntent()
  }

  fun getUserName(context: Context) {
    viewModelScope.launch {
      val username = repository.getUserName(context)
      username.collect {
        _name.postValue(it)
      }
    }
  }

  fun handleIntent(){
    viewModelScope.launch {
      characterIntent.consumeAsFlow().collect{intent ->
        when(intent){
          is CharacterIntent.FetchCharacters -> getCharacters()
        }
      }
    }
    
  }
 private fun getCharacters() {
    viewModelScope.launch(Dispatchers.IO) {
      _viewState.value = UiState.LoadingState
      repository.getCharacters()
        .catch {exception ->
          _viewState.value = UiState.Error(exception.message ?: "Something went wrong")
        }
        .collect { characters ->
        _viewState.value = UiState.Characters(data = characters)
      }

    }
  }

  fun saveUserPreferences(name: String, funFact: String, context: Context) {
    viewModelScope.launch {
      repository.saveUserPreferences(name, funFact, context)
    }
  }
}