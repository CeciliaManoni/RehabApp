package com.cecilia.apprehabilitacion.presentation.registerprofesional

import android.widget.Spinner
import com.cecilia.apprehabilitacion.domain.interactor.registerProfesionalInteractor.RegisterProfesionalInteractor
import com.cecilia.apprehabilitacion.presentation.login.LoginInterface

interface RegisterProfesionalInterface {

    interface RegisterProfView {
        fun showError(msgError:String)
        fun singUp()
        fun navigateToInstitution()
        fun addInstitution()
        fun profileInformation()
        fun setDate()
    }

    interface RegisterProfPresenter{
        fun attachView(view: RegisterProfesionalInterface.RegisterProfView)
        fun detachView()
        fun isViewAttached():Boolean
        fun singUp(fullname:String, email:String, password:String)
        fun profileInformation(fullname:String,email:String,psw:String,id:String,birth:String)
        fun checkEmptyDate(birth: String):Boolean
        fun checkEmptyFields(fullname: String, birth:String, id:String, inst:String):Boolean
        fun checkSpinnersContent(province:String, city:String, profesion:String, inst:String)
        fun checkValidEmail(email: String):Boolean
        fun checkEmptyPasswordAndEmail(pw1:String, pw2:String, email: String):Boolean
        fun checkPasswordMatch(pw1: String, pw2:String):Boolean
    }
}