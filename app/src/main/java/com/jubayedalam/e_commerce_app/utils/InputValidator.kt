package com.jubayedalam.e_commerce_app.utils

import android.icu.util.Calendar
import android.util.Patterns

object InputValidator {

    fun validateEmail(email: String): String? {
        if (email.isEmpty()) {
            return "⚠ Email is required"
        }
        if (email[0].isUpperCase()) {
            return "⚠ Email must start with a lowercase letter"
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return "⚠ Invalid Email Address"
        }
        return null
    }

    fun validatePassword(password: String): String? {
        if (password.isEmpty()){
            return "⚠ Password is required"
        }
        if (password.length < 8) {
            return "⚠ Minimum 8 Character Password"
        }
        if (!password.matches(".*\\d.*".toRegex())) {
            return "⚠ At least 1 number is required"
        }
        if (!password.matches(".*[A-Z].*".toRegex())) {
            return "⚠ Must Contain 1 Upper-case Character"
        }
        if (!password.matches(".*[a-z].*".toRegex())) {
            return "⚠ Must Contain 1 Lower-case Character"
        }
        if (!password.matches(".*[@#\$%^&+=].*".toRegex())) {
            return "Must Contain 1 Special Character (@#\$%^&+=)"
        }
        return null
    }

    fun validatePhone(phone: String): String? {
        if (phone.isEmpty()){
            return "⚠ Phone Number is required"
        }
        if (!phone.matches("^[0-9]+$".toRegex())) {
            return "⚠ Must be all digits"
        }
        if (phone.length != 10 && !(phone.startsWith("0") && phone.length == 11)) {
            return "⚠ Phone number must be either 10 digits or 11 digits starting with '0'"
        }
        return null
    }

    fun validateAddress(address: String): String? {
        if (address.isBlank()) {
            return "⚠ Address is required"
        }
        val addressComponents = address.split(",")
        if (addressComponents.size < 3) {
            return "⚠ Address must include city, zip code and country"
        }
        return null
    }

    fun validateName(fullName: String): String? {
        if (fullName.isBlank()) {
            return "⚠ Full Name is required"
        }
        return null
    }
    fun validateFirstName(fullName: String): String? {
        if (fullName.isBlank()) {
            return "⚠ First Name is required"
        }
        return null
    }
    fun validateLastName(fullName: String): String? {
        if (fullName.isBlank()) {
            return "⚠ Last Name is required"
        }
        return null
    }

    fun validateCity(city: String): String? {
        if (city.isBlank()) {
            return "⚠ City/District is required"
        }
        return null
    }

    fun validateZip(zip: String): String? {
        if (zip.isBlank()) {
            return "⚠ Zip is required"
        }
        return null
    }

    fun validateCardNumber(cardNumber: String): String? {
        if (cardNumber.isBlank()) {
            return "⚠ Card Number is required"
        }

        // Remove any non-digit characters from the card number
        val sanitizedCardNumber = cardNumber.replace("\\D".toRegex(), "")

        // Check if the card number contains only digits and has a valid length
        if (!sanitizedCardNumber.matches("\\d+".toRegex()) || sanitizedCardNumber.length < 13 || sanitizedCardNumber.length > 19) {
            return "⚠ Invalid Card Number"
        }

        // You can add more sophisticated checks such as Luhn algorithm validation here

        return null
    }

    fun validateCardholderName(cardholderName: String): String? {
        if (cardholderName.isBlank()) {
            return "⚠ Cardholder Name is required"
        }
        // You can add additional validation rules for cardholder name if needed
        return null
    }

    fun validateCardExpiration(expirationMonth: Int?, expirationYear: Int?): String? {
        if (expirationMonth == null || expirationYear == null) {
            return "⚠ Expiration month and year are required"
        }

        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        val currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1 // January is 0

        if (expirationYear < currentYear || (expirationYear == currentYear && expirationMonth < currentMonth)) {
            return "⚠ Card has expired"
        }

        // You can add more advanced validation logic if required
        return null
    }

    fun validateCVV(cvv: String?): String? {
        if (cvv.isNullOrBlank()) {
            return "⚠ CVV is required"
        }
        if (cvv.length != 3 && cvv.length != 4) {
            return "⚠ CVV must be 3 or 4 digits"
        }
        if (!cvv.matches("\\d+".toRegex())) {
            return "⚠ CVV must contain only digits"
        }
        return null
    }

    fun validateOTP(otp: String?): String? {
        if (otp.isNullOrBlank()) {
            return "⚠ OTP is required"
        }
        if (!otp.matches("\\d+".toRegex())) {
            return "⚠ OTP must contain only digits"
        }
        if (otp.length != 6) {
            return "⚠ OTP must be 6 digits long"
        }
        return null
    }


}
