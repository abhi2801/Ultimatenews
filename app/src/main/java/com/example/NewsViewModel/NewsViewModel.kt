package com.example.NewsViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.model.NewsResponse
import com.example.repo.NewsRepo
import com.example.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject
@HiltViewModel
class NewsViewModel @Inject constructor(private val repo:NewsRepo):ViewModel() {
    private val _newsResponse=MutableLiveData<Resource<NewsResponse>>()
    val newsResponse:LiveData<Resource<NewsResponse>> get() = _newsResponse

    init {
        breakingNews("in")
    }
    fun breakingNews(country:String){
        _newsResponse.postValue(Resource.Loading())
        try {
            viewModelScope.launch {
                val data=repo.getBreakingNews(country)
                _newsResponse.postValue(handleResponse(data))
            }
        }catch (e:Exception){
            Log.e("breaking"," error :${e.message}")
        }
    }
    private fun handleResponse(response:Response<NewsResponse>):Resource<NewsResponse>{
        if (response.isSuccessful){
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Failed(response.message())
    }
}