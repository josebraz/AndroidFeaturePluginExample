package com.josebraz.myapplication

import android.app.Application
import com.josebraz.myapplication.base.AppHostContract
import com.josebraz.myapplication.base.FeatureContract

class MyApplication: Application(), AppHostContract {

    private val features = mutableListOf<FeatureContract>()

    override fun addFeature(featureContract: FeatureContract) {
        features.add(featureContract)
    }

    override fun removeFeature(featureContract: FeatureContract) {
        features.remove(featureContract)
    }

    override fun getFeatures(): List<FeatureContract> = features

    override fun getApplicationName(): String = getString(R.string.app_name)
}