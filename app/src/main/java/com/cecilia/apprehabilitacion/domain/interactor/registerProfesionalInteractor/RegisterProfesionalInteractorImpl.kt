package com.cecilia.apprehabilitacion.domain.interactor.registerProfesionalInteractor

import com.cecilia.apprehabilitacion.base.Base
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class RegisterProfesionalInteractorImpl: RegisterProfesionalInteractor {

    private lateinit var database: DatabaseReference

    override fun signUp(fullname: String, email: String,password: String,
        listener: RegisterProfesionalInteractor.RegisterCallback) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password).addOnCompleteListener { itRegister ->
            if (itRegister.isSuccessful){
                val profileUpdate:UserProfileChangeRequest = UserProfileChangeRequest.Builder()
                    .setDisplayName(fullname)
                    .build()
                FirebaseAuth.getInstance().currentUser?.updateProfile(profileUpdate)?.addOnCompleteListener {
                    if(it.isSuccessful){
                        listener.onRegisterSuccess()
                    }
                }
            }
            else{
                listener.onRegisterFailure(itRegister.exception?.message.toString())
            }
        }
    }

    override fun profileInformation(fullname: String, email: String, password: String, id: String, birth:String,
                                    listener: RegisterProfesionalInteractor.ProfileInformationCallback) {

     /*   database = Firebase.database.reference
        database.child("users").child(id).setValue(fullname)
            .addOnSuccessListener {
                listener.onProfileSuccess()
            }
            .addOnFailureListener {
                listener.onProfileFailure("Falló la carga")
            }
    */}
}