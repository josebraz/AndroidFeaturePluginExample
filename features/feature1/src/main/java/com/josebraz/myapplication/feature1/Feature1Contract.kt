package com.josebraz.myapplication.feature1

import com.josebraz.myapplication.base.FeatureContract
import com.josebraz.myapplication.base.FeatureMenuBurger

class Feature1Contract: FeatureContract {

    override val featureMenuBurger = FeatureMenuBurger(
        iconId = R.drawable.ic_feature1,
        titleId = R.string.feature1_name,
        activity = Feature1Activity::class.java
    )
}