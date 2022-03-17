package com.cecilia.apprehabilitacion.presentation.registerprofesional

import com.cecilia.apprehabilitacion.presentation.login.LoginInterface

interface RegisterProfesionalInterface {

    interface RegisterProfView{
        fun showError(msgError:String)
        fun singUp()
        fun navigateToInstitution()
    }

    interface RegisterProfPresenter{
        fun attachView(view: RegisterProfesionalInterface.RegisterProfView)
        fun detachView()
        fun isViewAttached():Boolean
        fun singUp(fullname:String, email:String, password:String)
        fun checkEmptyFields(password:String):Boolean
        fun checkValidEmail(email: String):Boolean
        fun checkEmptyPassword(pw1:String, pw2:String):Boolean
        fun checkPasswordMatch(pw1: String, pw2:String):Boolean
    }
}