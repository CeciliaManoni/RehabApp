package com.cecilia.apprehabilitacion.domain.interactor.registerProfesionalInteractor

interface RegisterProfesionalInteractor {
    interface RegisterCallback{
        fun onRegisterSucces()
        fun onRegisterFailure(errorMsg: String)

    }

    fun signUp(fullname: String,email: String, password: String,listener: RegisterCallback)

    interface ProfileInformationCallback{
        fun onProfileSucces()
        fun onProfileFailure(errorMsg: String)

    }

    fun profileInformation(fullname: String, email: String, password: String,
                           id: String, listener: ProfileInformationCallback)

}