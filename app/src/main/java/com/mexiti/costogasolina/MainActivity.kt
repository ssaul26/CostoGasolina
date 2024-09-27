package com.mexiti.costogasolina

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mexiti.costogasolina.ui.theme.CostoGasolinaTheme
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CostoGasolinaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CostGasLayout("Android")
                }
            }
        }
    }
}

@Composable
fun CostGasLayout(name: String) {
    var precioEntradaLitro by remember {
        mutableStateOf("0.0")
    }
    var cantEntradaLitros by remember {
        mutableStateOf("0.0")
    }
    var propinaEntrada by remember {
        mutableStateOf("0.0")
    }
    var recibirPropina by remember {
        mutableStateOf(false)
    }

    val precioLitro = precioEntradaLitro.toDoubleOrNull() ?: 0.0
    val cantLitros = cantEntradaLitros.toDoubleOrNull() ?: 0.0
    val propina = propinaEntrada.toDoubleOrNull() ?: 0.0
    val total = CalculateMonto(precioLitro,cantLitros, recibirPropina = recibirPropina, propina = propina )

    Column {
        Text(
            text = stringResource(R.string.calcular_monto),

            )
       EditNumberField(
           label = R.string.ingresa_gasolina,
           leadingIcon = R.drawable.money_gas ,
           keyboardsOptions = KeyboardOptions.Default.copy(
               keyboardType = KeyboardType.Number,
               imeAction = ImeAction.Next
           ),
           value = precioEntradaLitro,
           onValueChanged = {precioEntradaLitro = it}
       )
        EditNumberField(
            label = R.string.litros,
            leadingIcon = R.drawable.gasolina,
            keyboardsOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            value = cantEntradaLitros,
            onValueChanged = {cantEntradaLitros = it}
        )
        EditNumberField(
            label = R.string.string_propina,
            leadingIcon = R.drawable.propina,
            keyboardsOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            value = propinaEntrada,
            onValueChanged = { propinaEntrada = it}
        )
        AddTip(recibirPropina = recibirPropina,
            onTipCheckedChange = {recibirPropina = it}

        )


        Text(
           text = stringResource(id = R.string.monto_total, total)
        )


    }

}

@Composable
fun EditNumberField(
    @StringRes label: Int,
    @DrawableRes leadingIcon: Int,
    keyboardsOptions:KeyboardOptions,
    value: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier
){
    TextField(
        label = { Text(text = stringResource(id = label))  },
        value = value,
        singleLine = true,
        leadingIcon = { Icon(painter = painterResource(id = leadingIcon) , contentDescription = null) },
        keyboardOptions = keyboardsOptions,
        modifier = modifier,
        onValueChange = onValueChanged
    )

}

fun CalculateMonto(
    precio: Double,
    cantLitros: Double,
    propina: Double,
    recibirPropina: Boolean
): String{
    var monto = precio * cantLitros
    if (recibirPropina){
        monto += propina
    }

    return NumberFormat.getCurrencyInstance().format(monto)
}

@Composable
fun AddTip(
    recibirPropina: Boolean,
    onTipCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
){
    Row (
        modifier = modifier
            .fillMaxWidth()
            .size(70.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            text = stringResource(id = R.string.agregar_propina),
            modifier = Modifier.padding(20.dp)
        )
        Switch(
            checked = recibirPropina , onCheckedChange = onTipCheckedChange)
    }

}

@Preview(showBackground = true)
@Composable



fun CostGasLayoutPreview() {
    CostoGasolinaTheme {
        CostGasLayout("Android")
    }
}