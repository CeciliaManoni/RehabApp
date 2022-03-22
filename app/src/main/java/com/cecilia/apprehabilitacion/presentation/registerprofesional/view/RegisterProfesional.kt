package com.cecilia.apprehabilitacion.presentation.registerprofesional.view

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.cecilia.apprehabilitacion.R
import com.cecilia.apprehabilitacion.base.Base
import com.cecilia.apprehabilitacion.domain.interactor.registerProfesionalInteractor.RegisterProfesionalInteractorImpl
import com.cecilia.apprehabilitacion.presentation.institution.view.Institutionact
import com.cecilia.apprehabilitacion.presentation.registerprofesional.RegisterProfesionalInterface
import com.cecilia.apprehabilitacion.presentation.registerprofesional.presenter.RegisterProfesionalPresenter
import kotlinx.android.synthetic.main.registerprofesional.*
import java.util.*

class RegisterProfesional : Base(), RegisterProfesionalInterface.RegisterProfView  {

    lateinit var presenter:RegisterProfesionalPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registerprofesional)

        presenter = RegisterProfesionalPresenter(RegisterProfesionalInteractorImpl())
        presenter.attachView(this)

        btn_profesional.setOnClickListener {
            singUp()
        }

        btn_calendar.setOnClickListener {
            setDate()
        }

        etxAddInstitution.setOnClickListener {
            addInstitution()
        }

    }

    override fun showError(msgError: String) {
        Toast(this, msgError)
    }

    override fun addInstitution() {
        //name_institution.visibility = View.VISIBLE
    }

    override fun profileInformation() {
        TODO("Not yet implemented")
    }

    override fun setDate() {
        //val birth:String = txtBirthDay.text.toString().trim()
        //presenter.setDate(birth)

        //Objeto tipo calendar
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val dayOfMonth = c.get(Calendar.DAY_OF_MONTH)

        //Generar la ventana desplegable con fechas a elegir
        val dpd = DatePickerDialog(this,
                    {view, year, month, dayOfMonth ->
                        txtBirthDay.text = "$dayOfMonth-${month + 1}-$year"
                    }, year, month, dayOfMonth)
        dpd.show()

    }

    override fun singUp() {
        val email:String = email_profesional.text.toString().trim()
        val psw1:String = password_profesional.text.toString().trim()
        val psw2:String = password_profesional_check.text.toString().trim()
        val fullname:String = name_profesional.text.toString().trim()
        val id:String = id_profesional.text.toString().trim()
        val institutionName:String = name_institution.text.toString().trim()
        val birth:String = txtBirthDay.text.toString().trim()

        if (presenter.checkEmptyFields(fullname,birth,id,institutionName)){
            name_institution.error = "Campo vacío"
            name_profesional.error = "Campo vacío"
            id_profesional.error = "Campo vacío"
            return
        }

        if(presenter.checkEmptyPasswordAndEmail(psw1,psw2,email)){
            password_profesional.error = "Campo vacío"
            password_profesional_check.error = "Campo vacío"
            email_profesional.error = "Campo vacío"
            return
        }

        if(!presenter.checkValidEmail(email)){
            email_profesional.error = "Email inválido"
            return
        }

        if(!presenter.checkPasswordMatch(psw1,psw2)){
            password_profesional_check.error = "Las contraseñas no coinciden"
            password_profesional.error = "Las contraseñas no coinciden"
            return
        }

        if (presenter.checkEmptyDate(birth)){
            txtBirthDay.error = "Campo vacío"
            return
        }

       // name_institution.visibility = View.INVISIBLE

        presenter.singUp(fullname,email,psw1)
        presenter.profileInformation()
    }

    override fun navigateToInstitution() {
        val intent = Intent(this, Institutionact::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }
}