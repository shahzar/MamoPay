package com.shzlabs.mamopay.ui.base

import androidx.appcompat.app.AppCompatActivity
import com.shzlabs.mamopay.di.components.AppComponent
import com.shzlabs.mamopay.BaseApplication
import com.shzlabs.mamopay.util.navigation.NavHelper
import com.shzlabs.mamopay.di.ViewModelFactory
import javax.inject.Inject

abstract class BaseActivity: AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    fun getDiComponent() : AppComponent {
        return (application as BaseApplication).appComponent
    }

    override fun onBackPressed() {
        if(!NavHelper.pop(this)) {
            finish()
        }
    }

}