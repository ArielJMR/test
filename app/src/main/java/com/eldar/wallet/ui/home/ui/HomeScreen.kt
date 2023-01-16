package com.eldar.wallet.ui.home.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import com.eldar.wallet.R
import com.eldar.wallet.core.models.Card

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    viewModel.getInformationUser()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(modifier = Modifier) {
            Spacer(modifier = Modifier.padding(12.dp))
            HeaderComponent(viewModel)
            Spacer(modifier = Modifier.padding(22.dp))
            AvailableBalanceComponent(modifier = Modifier, viewModel)
            Spacer(modifier = Modifier.padding(12.dp))
            Row {
                ButtonGenerateQRComponent(modifier = Modifier, viewModel)
                Spacer(modifier = Modifier.padding(12.dp))
                ButtonGeneratePaymentComponent(modifier = Modifier, viewModel)
            }
            Spacer(modifier = Modifier.padding(12.dp))
            ListCardsComponent(viewModel)
        }
    }
}

@Composable
fun HeaderComponent(viewModel: HomeViewModel) {
    val name: String by viewModel.name.observeAsState(initial = "")

    Text(text = "Hola", fontSize = 16.sp)
    Text(text = name, fontSize = 28.sp, fontWeight = FontWeight.Bold)
}

@Composable
fun AvailableBalanceComponent(modifier: Modifier, viewModel: HomeViewModel) {
    val balance: Number by viewModel.balance.observeAsState(initial = 0)

    Box(
        modifier = modifier
            .fillMaxWidth(1f)
            .height(180.dp)
            .padding(2.dp)
            .background(Color(0xFFF4F4F4)), contentAlignment = Alignment.Center
    ) {
        Column {
            Text(text = "Saldo disponible", fontSize = 20.sp, textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.padding(6.dp))
            Text(
                text = "$$balance",
                fontSize = 38.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun ButtonGenerateQRComponent(modifier: Modifier, modelView: HomeViewModel) {
    Box(
        modifier = modifier
            .fillMaxWidth(0.5f)
            .height(130.dp)
            .background(Color(0xFFF4F4F4))
            .clickable { }, contentAlignment = Alignment.Center
    ) {
        Column(modifier = Modifier.clickable { modelView.goToGenerateQr() }) {
            Image(
                modifier = Modifier
                    .width(100.dp)
                    .height(32.dp),
                painter = painterResource(id = R.drawable.scan),
                contentDescription = "Icono de boton de generar QR",
                alignment = Alignment.Center
            )
            Spacer(modifier = Modifier.padding(6.dp))
            Text(
                text = "Generar QR",
                fontSize = 22.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center
            )
        }

    }
}

@Composable
fun ButtonGeneratePaymentComponent(modifier: Modifier.Companion, modelView: HomeViewModel) {
    Box(
        modifier = modifier
            .height(130.dp)
            .fillMaxWidth(1f)
            .background(Color(0xFFF4F4F4))
            .clickable { modelView.goToGeneratePayment() }, contentAlignment = Alignment.Center
    ) {
        Column {
            Image(
                modifier = Modifier
                    .width(100.dp)
                    .height(32.dp),
                painter = painterResource(id = R.drawable.cards),
                contentDescription = "Icono de boton de generar pago",
            )
            Spacer(modifier = Modifier.padding(6.dp))
            Text(
                text = "Generar Pago",
                fontSize = 22.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center
            )
        }

    }
}

@Composable
fun ListCardsComponent(viewModel: HomeViewModel) {
    val cards: List<Card> = viewModel.cards;

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = "Tus tarjetas", textAlign = TextAlign.Left, fontSize = 22.sp)
        Image(
            modifier = Modifier
                .width(28.dp)
                .height(28.dp)
                .clickable { viewModel.goToAddCard() },
            painter = painterResource(id = R.drawable.card_add),
            contentDescription = "Icono de boton de añadir tarjeta"
        )
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
                        .padding(PaddingValues(16.dp, 0.dp, 0.dp, 0.dp)),
                    contentAlignment = Alignment.CenterStart,
                ) {
                    Text(text = card.number, textAlign = TextAlign.Left)
                }
                Spacer(modifier = Modifier.padding(8.dp))
            }
        }
    }


}

