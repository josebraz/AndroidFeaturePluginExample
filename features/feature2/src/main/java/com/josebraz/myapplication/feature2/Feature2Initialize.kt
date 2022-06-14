package com.josebraz.myapplication.feature2

import android.content.Context
import androidx.startup.Initializer
import com.josebraz.myapplication.base.AppHostContract
import com.josebraz.myapplication.base.FeatureContract

class Feature2Initialize : Initializer<FeatureContract> {

    override fun create(context: Context): FeatureContract {
        return Feature2Contract().also {
            (context as AppHostContract).addFeature(it)
        }
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}