package edu.ucne.jesus_bonilla_ap2_p1.domain.amonestaciones.usecase


data class AmonestacionValidation(
    val isValid: Boolean,
    val error: String? = null
)

fun validateNombres(nombres: String): AmonestacionValidation {
    return when {
        nombres.isBlank() -> AmonestacionValidation(false, "El nombre no puede estar vacío")
        nombres.trim().length < 3 -> AmonestacionValidation(false, "El nombre debe tener al menos 3 caracteres")
        else -> AmonestacionValidation(true)
    }
}

fun validateRazon(razon: String): AmonestacionValidation {
    return when {
        razon.isBlank() -> AmonestacionValidation(false, "La razon no puede estar vacía")
        razon.trim().length < 3 -> AmonestacionValidation(false, "La razon debe tener al menos 3 letras")
        else -> AmonestacionValidation(true)
    }
}

fun validateMonto(creditos: String): AmonestacionValidation {
    return when {
        creditos.isBlank() -> AmonestacionValidation(false, "El monto no pueden estar vacíos")
        creditos.toDouble() <= 0.0 -> AmonestacionValidation(false, "El monto deben ser mayores a 0")
        else -> AmonestacionValidation(true)
    }
}

