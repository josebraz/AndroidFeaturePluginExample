package com.josebraz.myapplication.feature1

import android.content.Context
import androidx.startup.Initializer
import com.josebraz.myapplication.base.AppHostContract
import com.josebraz.myapplication.base.FeatureContract

class Feature1Initialize : Initializer<FeatureContract> {

    override fun create(context: Context): FeatureContract {
        return Feature1Contract().also {
            (context as AppHostContract).addFeature(it)
        }
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}