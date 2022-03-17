package com.cecilia.apprehabilitacion.domain.interactor.registerProfesionalInteractor

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest

class RegisterProfesionalInteractorImpl: RegisterProfesionalInteractor {
    override fun signUp(
        fullname: String,
        email: String,
        password: String,
        listener: RegisterProfesionalInteractor.RegisterCallback
    ) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password).addOnCompleteListener { itRegister ->
            if (itRegister.isSuccessful){
                val profileUpdate:UserProfileChangeRequest = UserProfileChangeRequest.Builder()
                    .setDisplayName(fullname)
                    .build()
                FirebaseAuth.getInstance().currentUser?.updateProfile(profileUpdate)?.addOnCompleteListener {
                    if(it.isSuccessful){
                        listener.onRegisterSucces()
                    }
                }
            }
            else{
                listener.onRegisterFailure(itRegister.exception?.message.toString())
            }
        }
    }
}