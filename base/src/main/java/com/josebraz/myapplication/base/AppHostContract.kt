package com.josebraz.myapplication.base

import android.app.Application

interface AppHostContract {

    fun addFeature(featureContract: FeatureContract)

    fun removeFeature(featureContract: FeatureContract)

    fun getFeatures(): List<FeatureContract>

    fun getApplicationName(): String

}