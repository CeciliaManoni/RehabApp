package com.cecilia.apprehabilitacion.presentation.login.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cecilia.apprehabilitacion.R
import com.cecilia.apprehabilitacion.base.Base
import com.cecilia.apprehabilitacion.presentation.login.LoginInterface
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Base(), LoginInterface.View {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_singin.setOnClickListener {
            singIn()
        }
    }

    override fun showError(msgError: String) {
        TODO("Not yet implemented")
    }

    override fun singIn() {
        TODO("Not yet implemented")
    }

    override fun forgotpsw() {
        TODO("Not yet implemented")
    }

    override fun singUp() {
        TODO("Not yet implemented")
    }
}