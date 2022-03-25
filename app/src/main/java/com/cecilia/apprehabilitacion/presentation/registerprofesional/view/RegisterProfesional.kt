package com.cecilia.apprehabilitacion.presentation.registerprofesional.view

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
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
    lateinit var email:String
    lateinit var psw1:String
    lateinit var psw2:String
    lateinit var fullname:String
    lateinit var id:String
    lateinit var institutionName:String
    lateinit var birth:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registerprofesional)

        presenter = RegisterProfesionalPresenter(RegisterProfesionalInteractorImpl())
        presenter.attachView(this)

        btn_calendar.setOnClickListener {
            setDate()
        }
        btn_profesional.setOnClickListener {
            singUp()
            //profileInformation()
        }
        etxAddInstitution.setOnClickListener {
            addInstitution()
        }

        spinnerProvince()
    }

    override fun showError(msgError: String) {
        Toast(this, msgError)
    }

    override fun addInstitution() {
        //name_institution.visibility = View.VISIBLE
    }

    override fun profileInformation() {
        presenter.profileInformation(fullname,email,psw1,id,birth)
    }

    override fun setDate() {
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

    override fun spinnerProvince() {
        val adapter = ArrayAdapter.createFromResource(this,
            R.array.province,
            android.R.layout.simple_spinner_item)
        presenter.spinnerProvince(adapter, spinner_province)
    }

    override fun spinnerCity(province: String?) {
        Toast(this, "llegue al sppiner city")
    }

    override fun singUp() {
        email = email_profesional.text.toString().trim()
        psw1 = password_profesional.text.toString().trim()
        psw2 = password_profesional_check.text.toString().trim()
        fullname = name_profesional.text.toString().trim()
        id = id_profesional.text.toString().trim()
        institutionName = name_institution.text.toString().trim()
        birth = txtBirthDay.text.toString().trim()

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
    }

    override fun navigateToInstitution() {
        val intent = Intent(this, Institutionact::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }
}