package com.mexiti.costogasolina

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import java.text.NumberFormat

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Test
    fun test_calculateMonto_correctValues() {
        // Obtén el contexto de la aplicación para verificar el entorno de pruebas
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.mexiti.costogasolina", appContext.packageName)

        // Datos de entrada: precio de gasolina y cantidad de litros
        val precio = 22.35
        val cantLitros = 40.0
        val propina = 0.0
        val recibirPropina = false

        // Calcula el monto esperado (22.35 * 40.0)
        val montoEsperado = NumberFormat.getCurrencyInstance().format(precio * cantLitros)

        // Llama a la función que deseas probar
        val montoCalculado = CalculateMonto(precio, cantLitros, propina, recibirPropina)

        // Verifica que el resultado de la función sea correcto
        assertEquals(montoEsperado, montoCalculado)
    }
}
