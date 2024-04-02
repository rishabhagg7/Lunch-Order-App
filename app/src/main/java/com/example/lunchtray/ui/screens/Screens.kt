package com.example.lunchtray.ui.screens

import androidx.annotation.StringRes
import com.example.lunchtray.R

enum class Screens(
    @StringRes val title: Int
){
    Start(R.string.app_name),
    Entree(R.string.choose_entree),
    SideDish(R.string.choose_side_dish),
    Accompaniment(R.string.choose_accompaniment),
    Summary(R.string.order_checkout)
}