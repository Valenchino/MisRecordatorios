
package com.example.miaplicacion.misrecordatorios

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                PantallaRecordatorios()
            }
        }
    }
}

@Composable
fun PantallaRecordatorios() {
    val estado: EstadoPantalla =
        EstadoPantalla.Exito(recordatoriosValidos)

    Scaffold(
        topBar = {
            Surface(
                color = MaterialTheme.colorScheme.primary
            ) {
                Text(
                    text = "Mis Recordatorios",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Text(
                text = "Organizá tus tareas",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Pendientes: ${recordatoriosPendientes.size} | " +
                        "Prioridad alta: ${recordatoriosAltaPrioridad.size}"
            )

            Spacer(modifier = Modifier.height(16.dp))

            when (estado) {
                is EstadoPantalla.Cargando -> {
                    CircularProgressIndicator()
                }

                is EstadoPantalla.Error -> {
                    Text(estado.mensaje)
                }

                is EstadoPantalla.Exito -> {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(estado.recordatorios) { recordatorio ->
                            TarjetaRecordatorio(recordatorio)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TarjetaRecordatorio(recordatorio: Recordatorio) {

    val colorCategoria = Color(
        coloresCategorias[recordatorio.categoria.nombre]
            ?: 0xFF757575
    )

    val textoEstado = when (recordatorio.estado) {
        is EstadoRecordatorio.Pendiente -> "Pendiente"
        is EstadoRecordatorio.Completado -> "Completado"
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = recordatorio.titulo,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Categoría: ${recordatorio.categoria.nombre}",
                color = colorCategoria
            )

            Text(
                text = "Prioridad: ${recordatorio.prioridad}"
            )

            Text(
                text = "Estado: $textoEstado"
            )

            recordatorio.descripcion?.let {
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}
