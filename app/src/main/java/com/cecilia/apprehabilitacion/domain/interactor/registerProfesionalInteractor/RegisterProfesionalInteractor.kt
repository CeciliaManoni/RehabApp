package com.cecilia.apprehabilitacion.domain.interactor.registerProfesionalInteractor

interface RegisterProfesionalInteractor {
    interface RegisterCallback{
        fun onRegisterSucces()
        fun onRegisterFailure(errorMsg: String)

    }
    interface SpinnerCallback{

    }

    fun signUp(fullname: String,email: String, password: String,listener: RegisterCallback)

}