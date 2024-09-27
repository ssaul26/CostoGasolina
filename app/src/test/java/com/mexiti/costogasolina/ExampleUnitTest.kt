package com.mexiti.costogasolina

import org.junit.Test
import org.junit.Assert.*
import java.text.NumberFormat

class ExampleUnitTest {

    @Test
    fun test_calculateMonto_correctValues() {
        // Datos de entrada: precio de la gasolina y cantidad de litros
        val precio = 22.35
        val cantLitros = 40.0
        val propina = 0.0 // No se aplica propina en este caso
        val recibirPropina = false

        // Calcula el monto esperado (22.35 * 40.0)
        val montoEsperado = NumberFormat.getCurrencyInstance().format(precio * cantLitros)

        // Llama a la función que quieres probar
        val montoCalculado = CalculateMonto(precio, cantLitros, propina, recibirPropina)

        // Verifica que el monto calculado sea igual al esperado
        assertEquals(montoEsperado, montoCalculado)
    }
}
