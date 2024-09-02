package br.com.fitogether.api.core.extension

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.Date

fun String.isValidDate(): Boolean {
    val dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    return try {
        LocalDate.parse(this, dateFormat)
        true
    } catch (e: DateTimeParseException) {
        false
    }
}

fun String.formatDate(): Date {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy")
    return dateFormat.parse(this)
}

fun String.isAtLeast16YearsOld(): Boolean {
    val dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    val dateOfBirth: LocalDate = try {
        LocalDate.parse(this, dateFormat)
    } catch (e: DateTimeParseException) {
        return false
    }

    val today = LocalDate.now()
    val sixteenYearsAgo = today.minus(Period.ofYears(16))

    return dateOfBirth.isBefore(sixteenYearsAgo)
}

fun String.isValidPassword(): Boolean {
    val hasUpperCase = ".*[A-Z].*".toRegex()
    val hasLowerCase = ".*[a-z].*".toRegex()
    val hasDigit = ".*\\d.*".toRegex()
    val hasSpecialChar = ".*[!@#$%^&*()_+\\-=\\[\\]{};':\"|,.<>/?].*".toRegex()

    return hasUpperCase.containsMatchIn(this) &&
        hasLowerCase.containsMatchIn(this) &&
        hasDigit.containsMatchIn(this) &&
        hasSpecialChar.containsMatchIn(this)
}

fun String.transformToFieldString(): String {
    val result = StringBuilder()

    for (char in this) {
        if (char.isUpperCase()) {
            result.append('_')
            result.append(char.lowercaseChar())
        } else {
            result.append(char)
        }
    }

    return result.toString()
}
