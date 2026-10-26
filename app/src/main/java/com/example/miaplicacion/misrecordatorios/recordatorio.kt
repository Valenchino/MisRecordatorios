
package com.example.miaplicacion.misrecordatorios

data class Recordatorio(
    val id: Int,
    val titulo: String,
    val descripcion: String? = null,
    val categoria: Categoria,
    val prioridad: Prioridad,
    val estado: EstadoRecordatorio,
    val ubicacion: String? = null,
    val fechaAviso: String? = null
)

data class Categoria(
    val nombre: String
)

enum class Prioridad {
    ALTA, MEDIA, BAJA
}

sealed class EstadoRecordatorio {
    data object Pendiente : EstadoRecordatorio()
    data object Completado : EstadoRecordatorio()
}

sealed class EstadoPantalla {
    data object Cargando : EstadoPantalla()

    data class Exito(
        val recordatorios: List<Recordatorio>
    ) : EstadoPantalla()

    data class Error(
        val mensaje: String
    ) : EstadoPantalla()
}

fun validarRecordatorio(
    recordatorio: Recordatorio
): Boolean {
    return recordatorio.id > 0 &&
            recordatorio.titulo.isNotBlank() &&
            recordatorio.categoria.nombre.isNotBlank()
}
