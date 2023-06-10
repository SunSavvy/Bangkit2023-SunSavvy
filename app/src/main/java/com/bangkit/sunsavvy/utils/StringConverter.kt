package com.bangkit.sunsavvy.utils

class StringConverter {

    companion object {
        fun arabicToRoman(number: Int): String {
            val values = listOf(1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1)
            val symbols = listOf("M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I")

            var remainingNumber = number
            var romanNumeral = ""

            for (i in values.indices) {
                while (remainingNumber >= values[i]) {
                    romanNumeral += symbols[i]
                    remainingNumber -= values[i]
                }
            }

            return romanNumeral
        }
    }

}