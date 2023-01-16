package com.eldar.wallet.ui.new_card.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eldar.wallet.core.models.Card
import kotlinx.coroutines.launch

@Composable
fun NewCardScreen(viewModel: NewCardViewModel) {
    viewModel.getInformationCards();
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        NewCard(Modifier.align(Alignment.Center), viewModel)
    }
}

@Composable
fun NewCard(modifier: Modifier, viewModel: NewCardViewModel) {
    val number: String by viewModel.number.observeAsState(initial = "")
    val name: String by viewModel.name.observeAsState(initial = "")
    val expiration: String by viewModel.expiration.observeAsState(initial = "")
    val cvv: String by viewModel.cvv.observeAsState(initial = "")
    val addNewCardEnabled: Boolean by viewModel.addNewCardEnabled.observeAsState(initial = false)
    val typeCard: String by viewModel.type.observeAsState(initial = "")

    val coroutineScope = rememberCoroutineScope()

    Column(modifier = modifier) {
        BackButton(viewModel)
        Spacer(modifier = Modifier.padding(12.dp))
        Text(text = "Agrega una nueva tarjeta", fontSize = 22.sp, fontWeight = FontWeight.Medium)
        Spacer(modifier = Modifier.padding(18.dp))
        NumberField(number, viewModel) { viewModel.onAddCardFormChanged(it, name, expiration, cvv) }
        Spacer(modifier = Modifier.padding(6.dp))
        NameField(name) { viewModel.onAddCardFormChanged(number, it, expiration, cvv) }
        Spacer(modifier = Modifier.padding(6.dp))
        ExpirationField(expiration) { viewModel.onAddCardFormChanged(number, name, it, cvv) }
        Spacer(modifier = Modifier.padding(6.dp))
        CVVField(cvv) { viewModel.onAddCardFormChanged(number, name, expiration, it) }
        Spacer(modifier = Modifier.padding(6.dp))
        NewCardButton(addNewCardEnabled) {
            coroutineScope.launch {
                viewModel.onAddNewCardSelected(
                    Card(
                        typeCard,
                        name,
                        number,
                        cvv,
                        expiration
                    )
                )
            }
        }
    }
}

@Composable
fun NumberField(number: String, viewModel: NewCardViewModel, onTextFieldChanged: (String) -> Unit) {
    TextField(
        value = number,
        onValueChange = { onTextFieldChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Número de tarjeta") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFF111010),
            backgroundColor = Color(0xFFE2E2E2),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        trailingIcon = {
            Image(
                modifier = Modifier
                    .width(100.dp)
                    .height(32.dp),
                painter = painterResource(id = viewModel.getImageTypeCard(number)),
                contentDescription = "Icono de tarjeta",
            )
        }
    )
}

@Composable
fun NameField(name: String, onTextFieldChanged: (String) -> Unit) {
    TextField(
        value = name,
        onValueChange = { onTextFieldChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Nombre de titular de la tarjeta") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFF111010),
            backgroundColor = Color(0xFFE2E2E2),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun CVVField(number: String, onTextFieldChanged: (String) -> Unit) {
    TextField(
        value = number,
        onValueChange = { onTextFieldChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "CVV") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFF111010),
            backgroundColor = Color(0xFFE2E2E2),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun ExpirationField(number: String, onTextFieldChanged: (String) -> Unit) {
    TextField(
        value = number,
        onValueChange = { onTextFieldChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Vencimiento") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFF111010),
            backgroundColor = Color(0xFFE2E2E2),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun BackButton(viewModel: NewCardViewModel) {
    Icon(
        Icons.Rounded.ArrowBack,
        contentDescription = "Boton de volver hacia atras",
        modifier = Modifier.clickable { }
    )
}

@Composable
fun NewCardButton(loginEnabled: Boolean, onLoginSelected: () -> Unit) {
    Button(
        onClick = { onLoginSelected() },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFF292524),
            disabledBackgroundColor = Color(0xB7BBB9B9),
            contentColor = Color.White,
            disabledContentColor = Color.White
        ), enabled = loginEnabled
    ) {
        Text(text = "INGRESAR", color = Color.White)
    }
}