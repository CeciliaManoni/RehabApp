package com.cecilia.apprehabilitacion.presentation.registerprofesional.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cecilia.apprehabilitacion.R
import com.cecilia.apprehabilitacion.base.Base
import com.cecilia.apprehabilitacion.presentation.institution.view.Institutionact
import com.cecilia.apprehabilitacion.presentation.registerprofesional.RegisterProfesionalInterface
import com.cecilia.apprehabilitacion.presentation.registerprofesional.presenter.RegisterProfesionalPresenter

class RegisterProfesional : Base(), RegisterProfesionalInterface.RegisterProfView  {

    lateinit var presenter:RegisterProfesionalPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registerprofesional)

        presenter = RegisterProfesionalPresenter()
        presenter.attachView(this)

    }

    override fun showError(msgError: String) {
        Toast(this, msgError)
    }

    override fun singUp() {
        TODO("Not yet implemented")
    }

    override fun navigateToInstitution() {
        val intent = Intent(this, Institutionact::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }
}