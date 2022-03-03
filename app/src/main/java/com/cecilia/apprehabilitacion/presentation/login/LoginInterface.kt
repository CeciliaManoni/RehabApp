package com.cecilia.apprehabilitacion.presentation.login

interface LoginInterface {

    interface View{
        fun showError(msgError:String)
        fun singIn()
        fun forgotpsw()
        fun singUp()
    }
}