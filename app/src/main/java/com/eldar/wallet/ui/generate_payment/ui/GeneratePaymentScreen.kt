package com.eldar.wallet.ui.generate_payment.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import com.eldar.wallet.core.models.Card
import com.eldar.wallet.ui.new_card.ui.NewCardButton
import kotlinx.coroutines.launch

@Composable
fun GeneratePaymentScreen(viewModel: GeneratePaymentViewModel) {
    val numberCardSelected: String by viewModel.numberCardSelected.observeAsState("")
    val generatePaymentIsEnabled: Boolean by viewModel.generatePaymentIsEnabled.observeAsState(initial = false)

    viewModel.getInformationUser()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(modifier = Modifier) {
            BackButton(viewModel)
            Spacer(modifier = Modifier.padding(12.dp))
            AmountPaymentComponent(numberCardSelected)
            Spacer(modifier = Modifier.padding(12.dp))
            ListCardsComponent(viewModel)
            GeneratePaymentButton(generatePaymentIsEnabled) {viewModel.onSelectedGeneratePayment()};
        }
    }
}

@Composable
fun BackButton(viewModel: GeneratePaymentViewModel) {
    Icon(
        Icons.Rounded.ArrowBack,
        contentDescription = "Boton de volver hacia atras",
        Modifier.clickable { viewModel.goBack() }
    )
}

@Composable
fun AmountPaymentComponent(numberCardSelected: String) {
    Text(text = "Tarjeta seleccionada", fontWeight = FontWeight.Medium, fontSize = 22.sp)
    Spacer(modifier = Modifier.padding(12.dp))
    AmountPaymentField(numberCardSelected)
}

@Composable
fun AmountPaymentField(numberCardSelected: String) {
    TextField(
        value = numberCardSelected,
        onValueChange = {},
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Número de tarjeta seleccionada") },
        readOnly = true,
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
fun ListCardsComponent(viewModel: GeneratePaymentViewModel) {
    val cards: List<Card> = viewModel.cards;

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = "Selecciona una tarjeta", textAlign = TextAlign.Left, fontSize = 22.sp)
    }

    Spacer(modifier = Modifier.padding(12.dp))

    LazyColumn {
        items(cards, key = { it.number }) { card ->
            Column {
                Box(
                    modifier = Modifier
                        .height(48.dp)
                        .fillMaxWidth(1f)
                        .background(Color(0xFFF4F4F4))
                        .padding(PaddingValues(16.dp, 0.dp, 0.dp, 0.dp)).clickable { viewModel.onPaymentFormChanged(card.number) },
                    contentAlignment = Alignment.CenterStart,
                ) {
                    Text(text = card.number, textAlign = TextAlign.Left)
                }
                Spacer(modifier = Modifier.padding(8.dp))
            }


        }
    }
}

@Composable
fun GeneratePaymentButton(loginEnabled: Boolean, onLoginSelected: () -> Unit) {
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
        Text(text = "Pagar", color = Color.White)
    }
}

