package com.josebraz.myapplication.feature2

import com.josebraz.myapplication.base.FeatureContract
import com.josebraz.myapplication.base.FeatureMenuBurger

class Feature2Contract: FeatureContract {

    override val featureMenuBurger = FeatureMenuBurger(
        iconId = R.drawable.ic_feature2,
        titleId = R.string.feature2_name,
        activity = Feature2Activity::class.java
    )
}