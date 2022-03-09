package com.cecilia.apprehabilitacion.presentation.login.presenter

import com.cecilia.apprehabilitacion.domain.interactor.loginInteractor.SingInInteractor
import com.cecilia.apprehabilitacion.presentation.login.LoginInterface

class LoginPresenter(SingInInteractor:SingInInteractor): LoginInterface.Presenter {

    var view:LoginInterface.LoginView? = null
    var singInInteractor:SingInInteractor? = null

    init {
        this.singInInteractor = SingInInteractor
    }

    override fun attachView(view: LoginInterface.LoginView) {
        this.view = view
    }

    override fun dettachView() {
        view = null
    }

    override fun isViewAttached(): Boolean {
        return view!=null
    }

    override fun singInUserWithEmailAndPassword(email: String, password: String) {
        singInInteractor?.singInWithEmailAndPassword(email,password,object:SingInInteractor.SingInCallback{
            override fun onSingInSuccess() {
                if (isViewAttached()) view?.navigateToInstitution()
            }

            override fun onSingInFailure(errorMsg: String) {
               if (isViewAttached()) view?.showError(errorMsg)
            }
        })
    }

    override fun emptyFields(email: String, password: String):Boolean {
        return email.isEmpty() || password.isEmpty()
    }

}