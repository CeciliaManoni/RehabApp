package com.cecilia.apprehabilitacion.domain.interactor.loginInteractor

interface SingInInteractor {

    interface SingInCallback{
        fun onSingInSuccess()
        fun onSingInFailure(errorMsg:String)
    }

    fun singInWithEmailAndPassword(email:String, password:String, listener:SingInCallback)
}