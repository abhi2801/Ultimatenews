package com.example.utils

sealed class Resource<T>( val data:T?=null, val msg:String?=null){
    class Loading<T>():Resource<T>()
    class Success<T>(data: T):Resource<T>(data)
    class Failed<T>(msg: String,data: T?=null):Resource<T>(data, msg)
}
