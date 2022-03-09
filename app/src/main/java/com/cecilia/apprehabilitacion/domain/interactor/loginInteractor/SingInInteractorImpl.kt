package com.cecilia.apprehabilitacion.domain.interactor.loginInteractor

import com.google.firebase.auth.FirebaseAuth

class SingInInteractorImpl:SingInInteractor {

    override fun singInWithEmailAndPassword(email: String,password: String,
                                            listener: SingInInteractor.SingInCallback) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password).addOnCompleteListener {
            if (it.isSuccessful) listener.onSingInSuccess()
            else listener.onSingInFailure(it.exception?.message!!)
        }
    }
}