package com.shzlabs.mamopay.util.navigation

import android.util.Log
import com.shzlabs.mamopay.R
import com.shzlabs.mamopay.ui.base.BaseActivity
import com.shzlabs.mamopay.ui.base.BaseFragment


object NavHelper {

    private val TAG = "NavHelper"

    fun pushFragment(baseActivity: BaseActivity, baseFragment: BaseFragment) {
        Log.d(TAG, "Pushed ${baseFragment.javaClass.simpleName}")
        baseActivity
            .supportFragmentManager
            .beginTransaction()
            .addToBackStack("")
            .replace(R.id.content, baseFragment)
            .commit()
    }


    fun pop(baseActivity: BaseActivity): Boolean {

        if (baseActivity.supportFragmentManager.backStackEntryCount <= 1) {
            return false
        }

        baseActivity
            .supportFragmentManager
            .popBackStack()

        return true
    }
}