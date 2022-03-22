package com.cecilia.apprehabilitacion.presentation.registerprofesional.presenter

import androidx.core.util.PatternsCompat
import com.cecilia.apprehabilitacion.base.Base
import com.cecilia.apprehabilitacion.domain.interactor.registerProfesionalInteractor.RegisterProfesionalInteractor
import com.cecilia.apprehabilitacion.domain.interactor.registerProfesionalInteractor.RegisterProfesionalInteractor.RegisterCallback
import com.cecilia.apprehabilitacion.presentation.registerprofesional.RegisterProfesionalInterface

class RegisterProfesionalPresenter(registerProfessionalInteractor: RegisterProfesionalInteractor): RegisterProfesionalInterface.RegisterProfPresenter {

    var view:RegisterProfesionalInterface.RegisterProfView? = null
    var registerProfessionalInteractor: RegisterProfesionalInteractor? = null

    init {
        this.registerProfessionalInteractor = registerProfessionalInteractor
    }

    override fun attachView(view: RegisterProfesionalInterface.RegisterProfView) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }

    override fun isViewAttached(): Boolean {
        return view != null
    }

    override fun singUp(fullname: String, email: String, password: String) {
        registerProfessionalInteractor?.signUp(fullname,email,password,object: RegisterCallback{
            override fun onRegisterSuccess() {
                view?.profileInformation()
            }

            override fun onRegisterFailure(errorMsg: String) {
                view?.showError(errorMsg)
            }

        })
    }

    override fun profileInformation(fullname:String,email:String,psw:String,id:String,birth:String) {
        registerProfessionalInteractor?.profileInformation(fullname,email,psw,id,birth,object:
            RegisterProfesionalInteractor.ProfileInformationCallback {
            override fun onProfileSuccess() {
                view?.navigateToInstitution()
            }

            override fun onProfileFailure(errorMsg: String) {
                view?.showError(errorMsg)
            }

        })
    }

    override fun checkEmptyDate(birth: String): Boolean {
        return birth.isEmpty()
    }

    override fun checkEmptyFields(fullname: String, birth:String, id:String, inst:String): Boolean {
        return fullname.isEmpty() or id.isEmpty() or inst.isEmpty()
    }

    override fun checkSpinnersContent(
        province: String,
        city: String,
        profesion: String,
        inst: String
    ) {
        TODO("Not yet implemented")
    }

    override fun checkValidEmail(email: String): Boolean {
        return PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()
    }

    override fun checkEmptyPasswordAndEmail(pw1: String, pw2: String, email: String): Boolean {
        return pw1.isEmpty() or pw2.isEmpty()
    }

    override fun checkPasswordMatch(pw1: String, pw2: String): Boolean {
        return pw1 == pw2
    }
}