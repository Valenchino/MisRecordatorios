
package com.example.miaplicacion.misrecordatorios

val categorias = setOf(
    Categoria("Estudio"),
    Categoria("Personal"),
    Categoria("Trabajo"),
    Categoria("Salud"),
    Categoria("Estudio")
)

val coloresCategorias = mapOf(
    "Estudio" to 0xFF6750A4,
    "Personal" to 0xFF008577,
    "Trabajo" to 0xFF1565C0,
    "Salud" to 0xFF2E7D32
)

val recordatorios = listOf(
    Recordatorio(
        id = 1,
        titulo = "Estudiar Kotlin",
        descripcion = "Repasar programación mobile",
        categoria = Categoria("Estudio"),
        prioridad = Prioridad.ALTA,
        estado = EstadoRecordatorio.Pendiente
    ),
    Recordatorio(
        id = 2,
        titulo = "Comprar alimentos",
        categoria = Categoria("Personal"),
        prioridad = Prioridad.MEDIA,
        estado = EstadoRecordatorio.Completado
    ),
    Recordatorio(
        id = 3,
        titulo = "Entregar actividad",
        categoria = Categoria("Estudio"),
        prioridad = Prioridad.ALTA,
        estado = EstadoRecordatorio.Pendiente
    ),
    Recordatorio(
        id = 4,
        titulo = "Hacer ejercicio",
        categoria = Categoria("Salud"),
        prioridad = Prioridad.BAJA,
        estado = EstadoRecordatorio.Pendiente
    )
)

val recordatoriosValidos =
    recordatorios.filter(::validarRecordatorio)

val recordatoriosPendientes =
    recordatoriosValidos.filter {
        it.estado is EstadoRecordatorio.Pendiente
    }

val recordatoriosAltaPrioridad =
    recordatoriosValidos.filter {
        it.prioridad == Prioridad.ALTA
    }
