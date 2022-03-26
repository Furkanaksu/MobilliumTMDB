package com.furkan.mobilliumcase.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.furkan.mobilliumcase.data.dto.ApiClient
import com.furkan.mobilliumcase.data.model.GenreModel
import com.furkan.mobilliumcase.data.repository.GenreRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.launch

@ViewModelScoped
class MainFragmentViewModel : ViewModel(){

    private val _getMovies = MutableLiveData<GenreModel>()
    val getMovies: LiveData<GenreModel>
        get() = _getMovies

    private val _getNewMovies = MutableLiveData<GenreModel>()
    val getNewMovies: LiveData<GenreModel>
        get() = _getNewMovies


     fun getAllDiscover() =
        viewModelScope.launch {
                GenreRepository(ApiClient.retrofit()).getMovies().let {
                    _getMovies.value = it
                }
        }

    fun getAllNews() =
        viewModelScope.launch {
            GenreRepository(ApiClient.retrofit()).getNewMoviews().let {
                _getNewMovies.value = it
            }
        }
}