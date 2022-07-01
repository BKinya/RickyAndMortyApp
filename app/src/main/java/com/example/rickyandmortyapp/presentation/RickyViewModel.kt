package com.example.rickyandmortyapp.presentation

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickyandmortyapp.domain.repository.RickyRepository
import com.example.rickyandmortyapp.models.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class RickyViewModel(
  private val repository: RickyRepository
) : ViewModel() {

  private val _name = MutableLiveData<String?>()
  val name: LiveData<String?>
    get() = _name

  private val _characters = MutableStateFlow(UiState(true, listOf(), false))
  val characters: StateFlow<UiState>
    get() = _characters

  fun getUserName(context: Context) {
    viewModelScope.launch {
      val username = repository.getUserName(context)
      username.collect {
        _name.postValue(it)
      }
    }
  }

  fun getCharacters() {
    viewModelScope.launch(Dispatchers.IO) {
      _characters.emit(UiState(true, listOf(), false))
      repository.getCharacters().collect {
        _characters.emit(UiState(false, it, false))
      }
    }
  }

  fun saveUserPreferences(name: String, funFact: String, context: Context) {
    viewModelScope.launch {
      repository.saveUserPreferences(name, funFact, context)
    }
  }

  fun readUserName(context: Context) {
    viewModelScope.launch {
      val name = repository.getUserName(context)
      name.collect { userName ->
        _name.postValue(userName)
      }
    }
  }
}