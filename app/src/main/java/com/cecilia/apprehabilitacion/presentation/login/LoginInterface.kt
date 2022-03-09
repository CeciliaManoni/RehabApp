package com.cecilia.apprehabilitacion.presentation.login

interface LoginInterface {

    interface LoginView{
        fun showError(msgError:String)
        fun singIn()
        fun navigateToPassword()
        fun navigateToRegister()
        fun navigateToInstitution()
    }

    interface Presenter{
        fun attachView(view: LoginView)
        fun dettachView()
        fun isViewAttached():Boolean
        fun singInUserWithEmailAndPassword(email:String, password:String)
        fun emptyFields(email:String, password:String):Boolean
    }
}