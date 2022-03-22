package com.cecilia.apprehabilitacion.presentation.registerprofesional.presenter

import android.app.DatePickerDialog
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.util.PatternsCompat
import com.cecilia.apprehabilitacion.domain.interactor.registerProfesionalInteractor.RegisterProfesionalInteractor
import com.cecilia.apprehabilitacion.domain.interactor.registerProfesionalInteractor.RegisterProfesionalInteractor.RegisterCallback
import com.cecilia.apprehabilitacion.presentation.registerprofesional.RegisterProfesionalInterface
import com.cecilia.apprehabilitacion.presentation.registerprofesional.view.RegisterProfesional
import kotlinx.android.synthetic.main.registerprofesional.view.*
import java.security.AccessController.getContext
import java.util.*

class RegisterProfesionalPresenter(registerProfessionalInteractor: RegisterProfesionalInteractor):RegisterProfesionalInterface.RegisterProfPresenter {

    var viewOriginal:RegisterProfesionalInterface.RegisterProfView? = null
    var viewDate: RegisterProfesionalPresenter? = null
    var registerProfessionalInteractor: RegisterProfesionalInteractor? = null
    var monthG = 0
    var yearG = 0
    var dayG = 0

    init {
        this.registerProfessionalInteractor = registerProfessionalInteractor
    }

    override fun attachView(view: RegisterProfesionalInterface.RegisterProfView) {
        this.viewOriginal = view
    }

    override fun detachView() {
        viewOriginal = null
    }

    override fun isViewAttached(): Boolean {
        return viewOriginal != null
    }

    override fun singUp(fullname: String, email: String, password: String) {
        registerProfessionalInteractor?.signUp(fullname,email,password,object: RegisterCallback{
            override fun onRegisterSucces() {
                viewOriginal?.navigateToInstitution()
            }

            override fun onRegisterFailure(errorMsg: String) {
                viewOriginal?.showError(errorMsg)
            }

        })
    }

    override fun profileInformation() {
        TODO("Not yet implemented")
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