package com.example.rickyandmortyapp.presentation

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickyandmortyapp.data.repository.RickyRepositoryImpl
import com.example.rickyandmortyapp.domain.repository.RickyRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class RickyViewModel(
  private val repository: RickyRepository
) : ViewModel() {

  private val _name = MutableLiveData<String>()
  val name: LiveData<String>
    get() = _name

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