package com.example.lunchtray.model

import java.text.NumberFormat

sealed class MenuItem (
    open val name: String,
    open val description: String,
    open val price: Double
){
    data class EntreeItem(
        override val name: String,
        override val description: String,
        override val price: Double
    ): MenuItem(name = name,description = description,price = price)

    data class SideDishItem(
        override val name: String,
        override val description: String,
        override val price: Double
    ): MenuItem(name = name,description = description,price = price)

    data class AccompanimentItem(
        override val name: String,
        override val description: String,
        override val price: Double
    ) : MenuItem(name, description, price)

    fun getFormattedPrice() : String{
        return NumberFormat.getCurrencyInstance().format(price)
    }
}