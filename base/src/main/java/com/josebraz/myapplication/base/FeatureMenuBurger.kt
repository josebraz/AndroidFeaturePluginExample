package com.josebraz.myapplication.base

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity

data class FeatureMenuBurger(
    @DrawableRes val iconId: Int,
    @StringRes val titleId: Int,
    val activity: Class<out AppCompatActivity>,
    val priority: Int = PRIORITY_LOWER
) {
    companion object {
        const val PRIORITY_HIGHER: Int = Int.MIN_VALUE
        const val PRIORITY_LOWER: Int = Int.MAX_VALUE
    }
}