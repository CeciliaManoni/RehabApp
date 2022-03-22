package com.cecilia.apprehabilitacion.domain.interactor.registerProfesionalInteractor

interface RegisterProfesionalInteractor {
    interface RegisterCallback{
        fun onRegisterSuccess()
        fun onRegisterFailure(errorMsg: String)

    }
    interface ProfileInformationCallback{
        fun onProfileSuccess()
        fun onProfileFailure(errorMsg: String)

    }

    fun signUp(fullname: String,email: String, password: String,listener: RegisterCallback)
    fun profileInformation(fullname: String, email: String, password: String,
                           id: String, birth:String, listener: ProfileInformationCallback)

}