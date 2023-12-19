package com.example

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment


fun Fragment.toast(msg:String,length:Int=Toast.LENGTH_LONG){
    requireContext().toast(msg,length)
}
fun Context.toast(msg: String,length: Int=Toast.LENGTH_LONG){
    Toast.makeText(this,msg,length).show()
}
fun <T:View> T.click(block:(T)->Unit)=setOnClickListener {
    block(it as T)
}
fun ProgressBar.show(){
    visibility=View.VISIBLE
}
fun ProgressBar.hide(){
    visibility=View.GONE
}