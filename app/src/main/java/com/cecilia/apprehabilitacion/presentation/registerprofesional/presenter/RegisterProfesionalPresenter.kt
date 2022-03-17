package com.cecilia.apprehabilitacion.presentation.registerprofesional.presenter

import androidx.core.util.PatternsCompat
import com.cecilia.apprehabilitacion.domain.interactor.loginInteractor.SingInInteractor
import com.cecilia.apprehabilitacion.domain.interactor.registerProfesionalInteractor.RegisterProfesionalInteractor
import com.cecilia.apprehabilitacion.domain.interactor.registerProfesionalInteractor.RegisterProfesionalInteractor.RegisterCallback
import com.cecilia.apprehabilitacion.presentation.login.LoginInterface
import com.cecilia.apprehabilitacion.presentation.registerprofesional.RegisterProfesionalInterface

class RegisterProfesionalPresenter(registerProfesionalInteractor: RegisterProfesionalInteractor):RegisterProfesionalInterface.RegisterProfPresenter {

    var view:RegisterProfesionalInterface.RegisterProfView? = null
    var registerProfesionalInteractor: RegisterProfesionalInteractor? = null

    init {
        this.registerProfesionalInteractor = registerProfesionalInteractor
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
        registerProfesionalInteractor?.signUp(fullname,email,password,object: RegisterCallback{
            override fun onRegisterSucces() {
                view?.navigateToInstitution()
            }

            override fun onRegisterFailure(errorMsg: String) {
                view?.showError(errorMsg)
            }

        })
    }

    override fun checkEmptyFields(email: String, password: String): Boolean {
        return email.isEmpty() or password.isEmpty()
    }

    override fun checkValidEmail(email: String): Boolean {
        return PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()
    }

    override fun checkEmptyPassword(pw1: String, pw2: String): Boolean {
        return pw1.isEmpty() or pw2.isEmpty()
    }

    override fun checkPasswordMatch(pw1: String, pw2: String): Boolean {
        return pw1 == pw2
    }
}