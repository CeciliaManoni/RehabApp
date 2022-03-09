package com.cecilia.apprehabilitacion.presentation.login.view

import android.content.Intent
import android.os.Bundle
import com.cecilia.apprehabilitacion.R
import com.cecilia.apprehabilitacion.base.Base
import com.cecilia.apprehabilitacion.domain.interactor.loginInteractor.SingInInteractorImpl
import com.cecilia.apprehabilitacion.presentation.institution.view.Institutionact
import com.cecilia.apprehabilitacion.presentation.login.LoginInterface
import com.cecilia.apprehabilitacion.presentation.login.presenter.LoginPresenter
import kotlinx.android.synthetic.main.activity_login.*

class LogInActivity : Base(), LoginInterface.LoginView {

    lateinit var presenter:LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        presenter = LoginPresenter(SingInInteractorImpl())
        presenter.attachView(this)
        btn_singin.setOnClickListener {
            singIn()
        }
    }

    override fun showError(msgError: String) {
        Toast(this, msgError)
    }

    override fun singIn() {
        val email:String = etxEmail.text.toString().trim()
        val psw:String = etxPassword.text.toString().trim()

        if(presenter.emptyFields(email,psw)){showError("Campos vacíos")}
        else{presenter.singInUserWithEmailAndPassword(email,psw)}
    }

    override fun navigateToInstitution() {
        startActivity(Intent(this,Institutionact::class.java))
    }
    
    override fun navigateToPassword() {

    }

    override fun navigateToRegister() {

    }


}